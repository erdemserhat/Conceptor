package com.erdemserhat.conceptor.data.database.repository.datamodel

import java.io.Serializable

data class Posts(
    val title:String,
    val transcription:String,
    val image: ByteArray,
    val id:Int?=null


):Serializable
