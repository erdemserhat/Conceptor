package com.erdemserhat.conceptor.data.database

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.erdemserhat.conceptor.data.database.repository.posts.Posts
import com.erdemserhat.conceptor.utils.AppConstants

class AppDatabase(val context: Context):DatabaseOperations {
    private lateinit var myDatabase:SQLiteDatabase
    init {
        initializeDatabase()
    }


    private fun initializeDatabase(){
        //Create or Open Database
        myDatabase= context.openOrCreateDatabase("Posts.db",MODE_PRIVATE,null)
        //Create Table
        myDatabase.execSQL("CREATE TABLE IF NOT EXISTS posts (id INTEGER PRIMARY KEY,title VARCHAR, transcription VARCHAR,image Blob)")

    }

    override fun insertPost(post:Posts) {
        var (title, transcription, image) = post
        myDatabase.execSQL("INSERT INTO posts (title,transcription,image) VALUES ('$title','$transcription','$image')")

    }

    override fun deletePost(post:Posts) {
        myDatabase.execSQL("DELETE FROM posts WHERE id=${post.id}")


    }

    override fun editPost(editedPost:Posts) {
        myDatabase.execSQL("UPDATE posts SET title=${editedPost.title} transcription=${editedPost.transcription} image=${editedPost.image}")

    }

    override fun readPosts(){
        val cursor: Cursor = myDatabase.rawQuery("SELECT * FROM posts",null)
        //Indexes
        val idIx:Int = cursor.getColumnIndex("id")
        val titleIx:Int = cursor.getColumnIndex("title")
        val transcriptionIx:Int = cursor.getColumnIndex("transcription")
        val imageIx:Int = cursor.getColumnIndex("image")
        //temp post
        var post: Posts? = null
        //return
        val postList:MutableList<Posts> = mutableListOf()


        while (cursor.moveToNext()){
            post = Posts(cursor.getString(titleIx),cursor.getString(transcriptionIx),cursor.getBlob(imageIx),cursor.getInt(idIx))
            postList.add(post)

        }
        AppConstants.postList=postList

    }


}