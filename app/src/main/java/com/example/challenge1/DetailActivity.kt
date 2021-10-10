package com.example.challenge1

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.bumptech.glide.Glide
import com.google.gson.Gson

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.setTitle(getString(R.string.detail_page))
        val imageView: ImageView = findViewById(R.id.imageView)
        val title: TextView = findViewById(R.id.title)
        val desc: TextView = findViewById(R.id.desc)

        val extras = intent.extras
        var item: Data? = null
        if (extras != null) {
            item = extras.getString("selected_item")?.let { getData(it) }
        }

        if (item?.custom_feature_image_url != null && !item.custom_feature_image_url.equals("")) {
            Glide.with(this)
                .load(item.custom_feature_image_url)
                .into(imageView)
        }
        title.setText(item?.post_title ?: "")
        desc.setText(
            HtmlCompat.fromHtml(
                item?.post_content ?: "",
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )
        )
    }

    fun getData(data : String):Data{
        val gson = Gson()
        return gson.fromJson(data, Data::class.java)
    }
}