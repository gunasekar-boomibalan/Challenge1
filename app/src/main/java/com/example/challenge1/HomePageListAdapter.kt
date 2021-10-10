package com.example.challenge1

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HomePageListAdapter(var data: ArrayList<Data>): RecyclerView.Adapter<HomePageListAdapter.ViewHolder>() {

    fun updateData(newUsers: ArrayList<Data>) {
        data.addAll(newUsers)
     notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = ViewHolder(

        LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = data.get(position)
        if (item.custom_feature_image_url != null && !item.custom_feature_image_url.equals("")) {
            Glide.with(holder.itemView.context)
                .load(item.custom_feature_image_url)
                .into(holder.imageView)
        }
        holder.title.setText(item.post_title ?: "")
        holder.desc.setText(
            HtmlCompat.fromHtml(
                item.post_content ?: "",
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )
        )
        holder.time.setText(formatDOB(item.post_date ?: ""))
        holder.commentsCount.setText((item.comment_count ?: 0).toString())
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context,DetailActivity::class.java)
            intent.putExtra("selected_item",gsonToJson(item))
            holder.itemView.context.startActivity(intent)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val title: TextView = view.findViewById(R.id.title)
        val desc: TextView = view.findViewById(R.id.desc)
        val time: TextView = view.findViewById(R.id.timeStamp)
        val commentsCount: TextView = view.findViewById(R.id.commentsCount)
    }

    override fun getItemCount() = data.size

    fun formatDOB(dob: String): String {
        try {
            val fromFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
            val toFormatter = SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH)
            val date = fromFormatter.parse(dob)
            val result = toFormatter.format(date)
            return result
        } catch (e: Exception) {
            return dob
        }
    }

    private fun gsonToJson(userData: Data) : String {
        val gson = Gson()
        val type = object : TypeToken<Data>() {}.type
        return gson.toJson(userData, type)
    }
}