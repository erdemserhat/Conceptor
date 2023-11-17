package com.erdemserhat.conceptor.data.database

import com.erdemserhat.conceptor.data.database.repository.posts.Posts

interface DatabaseOperations {

    fun insertPost(post:Posts)
    fun deletePost(post:Posts)
    fun editPost(editedPost:Posts)

    fun readPosts()
}