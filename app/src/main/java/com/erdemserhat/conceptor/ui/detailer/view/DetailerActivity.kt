package com.erdemserhat.conceptor.ui.detailer.view

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.navigation.NavType
import com.erdemserhat.conceptor.data.database.repository.posts.Posts
import com.erdemserhat.conceptor.databinding.DetailsActivityBinding
import com.erdemserhat.conceptor.ui.base.view.BaseActivity
import com.erdemserhat.conceptor.utils.AppConstants

class DetailerActivity: BaseActivity<DetailsActivityBinding> () {
    override fun getViewBinding(): DetailsActivityBinding {
        return DetailsActivityBinding.inflate(layoutInflater)


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent: Intent = intent
        val post: Posts? = intent.getSerializableExtra("post") as Posts
        val(title,transcription,image,id) = post!!
        binding.detailsActivityPostTitle.text=title
        binding.detailsActivityPostTranscription.text=transcription
        binding.detailsActivityPostImage.setImageBitmap(AppConstants.blobToBitMap(post.image))
}
}