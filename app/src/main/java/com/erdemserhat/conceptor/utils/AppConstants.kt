package com.erdemserhat.conceptor.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.erdemserhat.conceptor.data.database.repository.posts.Posts
import java.io.ByteArrayOutputStream

object AppConstants {
    var postList:MutableList<Posts> = mutableListOf()

    fun bitMapToBlob(bitmap:Bitmap):ByteArray{
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
        return byteArray

    }

    fun blobToBitMap(byteArray:ByteArray):Bitmap{
        val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
        return bitmap

    }
}