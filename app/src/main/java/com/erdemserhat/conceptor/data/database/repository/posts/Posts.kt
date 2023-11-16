package com.erdemserhat.conceptor.data.database.repository.posts

import java.sql.Blob

data class Posts(
    val title:String,
    val transcription:String,
    val image: Blob


)
