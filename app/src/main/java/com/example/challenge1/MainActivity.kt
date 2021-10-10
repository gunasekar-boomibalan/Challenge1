package com.example.challenge1

import android.content.ClipData.Item
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: ListViewModel
    lateinit var listView : RecyclerView
    lateinit var errorMessage : TextView
    lateinit var loadingView : ProgressBar
    lateinit var layoutManager : LinearLayoutManager
    val listAdapter = HomePageListAdapter(arrayListOf())
    var count  = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.setTitle(getString(R.string.homepage))
        listView = findViewById(R.id.homepageList)
        errorMessage = findViewById(R.id.listError)
        loadingView = findViewById(R.id.loadingView)
        layoutManager = LinearLayoutManager(this)
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.load(count)
        listView.layoutManager =layoutManager
        listView.adapter = listAdapter
        observeViewModel()
        listView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) { //check for scroll down
                    val visibleItemCount = layoutManager.getChildCount()
                    val totalItemCount = layoutManager.getItemCount()
                    val pastVisiblesItems = layoutManager.findFirstVisibleItemPosition()
                    if (Singleton.loading) {
                        if (visibleItemCount + pastVisiblesItems >= totalItemCount) {
                            Singleton.loading = false
                            count++
                            viewModel.load(count)
                            Log.i("Item_track","Count "+count)
                        }
                    }
                }
            }
        })
    }


    private fun observeViewModel() {
        viewModel.list.observe(this, Observer {list ->
            list?.let {
                listAdapter.updateData(it) }
        })
        viewModel.loadError.observe(this, Observer { isError ->
            errorMessage.visibility = if(isError == "") View.GONE else View.VISIBLE
        })
        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                loadingView.visibility = if(it) View.VISIBLE else View.GONE
                if(it) {
                    errorMessage.visibility = View.GONE
                }
            }
        })
    }
}