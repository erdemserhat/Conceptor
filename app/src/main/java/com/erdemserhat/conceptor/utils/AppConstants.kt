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

    fun blobToBitMap(byteArray:ByteArray):Bitmap?{
        if (byteArray != null) {
            val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
            return bitmap
        }

        return null

    }

    fun makeSmallerBitmap(image: Bitmap, maximumSize : Int) : Bitmap {
        var width = image.width
        var height = image.height

        val bitmapRatio : Double = width.toDouble() / height.toDouble()
        if (bitmapRatio > 1) {
            width = maximumSize
            val scaledHeight = width / bitmapRatio
            height = scaledHeight.toInt()
        } else {
            height = maximumSize
            val scaledWidth = height * bitmapRatio
            width = scaledWidth.toInt()
        }
        return Bitmap.createScaledBitmap(image,width,height,true)
    }

}