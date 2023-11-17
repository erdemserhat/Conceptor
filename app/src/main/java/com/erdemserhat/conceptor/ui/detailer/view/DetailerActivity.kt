package com.erdemserhat.conceptor.ui.detailer.view

import android.content.Intent
import android.os.Bundle
import com.erdemserhat.conceptor.data.database.repository.datamodel.Posts
import com.erdemserhat.conceptor.databinding.DetailsActivityBinding
import com.erdemserhat.conceptor.ui.base.view.BaseActivity
import com.erdemserhat.conceptor.utils.bitmap.BitmapOperations

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
        binding.detailsActivityPostImage.setImageBitmap(BitmapOperations.byteArrayToBitMap(post.image))
}
}