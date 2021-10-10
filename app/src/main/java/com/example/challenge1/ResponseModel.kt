package com.example.challenge1

data class ResponseModel(
    val data: ArrayList<Data>?,
    val status: Int?
)

data class Data(
    val ID: Int?,
    val bucket_id: Any?,
    val bucket_list_status: Int?,
    val cat_name: String?,
    val cat_slug: String?,
    val comment_count: Int?,
    val comment_status: String?,
    val custom_feature_image_url: String?,
    val guid: String?,
    val guidb: Any?,
    val image_url: Any?,
    val menu_order: Int?,
    val ping_status: String?,
    val pinged: String?,
    val post_author: Int?,
    val post_content: String?,
    val post_content_filtered: String?,
    val post_date: String?,
    val post_date_gmt: String?,
    val post_excerpt: String?,
    val post_mime_type: String?,
    val post_modified: String?,
    val post_modified_gmt: String?,
    val post_name: String?,
    val post_parent: Int?,
    val post_password: String?,
    val post_status: String?,
    val post_title: String?,
    val post_type: String?,
    val s3_thumbnail_image_260: String?,
    val thumbnail_image: String?,
    val to_ping: String?,
    val video_file: Any?,
    val video_link: Any?
)