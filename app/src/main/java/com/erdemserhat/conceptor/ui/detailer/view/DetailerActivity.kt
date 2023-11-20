package com.erdemserhat.conceptor.ui.detailer.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.erdemserhat.conceptor.R
import com.erdemserhat.conceptor.data.database.repository.datamodel.Posts
import com.erdemserhat.conceptor.databinding.DetailsActivityBinding
import com.erdemserhat.conceptor.ui.base.view.BaseActivity
import com.erdemserhat.conceptor.ui.base.view.BaseMVPView
import com.erdemserhat.conceptor.ui.detailer.presenter.DetailerMVPPresenter
import com.erdemserhat.conceptor.ui.detailer.presenter.DetailerPresenter
import com.erdemserhat.conceptor.ui.main.view.MainActivity
import com.erdemserhat.conceptor.utils.bitmap.BitmapOperations

class DetailerActivity : BaseActivity<DetailsActivityBinding>(), DetailerMVPView, BaseMVPView {
    private lateinit var presenter: DetailerMVPPresenter
    private lateinit var post: Posts
    override fun getViewBinding(): DetailsActivityBinding {
        return DetailsActivityBinding.inflate(layoutInflater)


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = DetailerPresenter()
        //presenter.attachView(this)
        val intent: Intent = intent
        post = intent.getSerializableExtra("post") as Posts
        val (title, transcription, image, id) = post!!
        binding.detailsActivityPostTitle.text = title
        binding.detailsActivityPostTranscription.text = transcription
        binding.detailsActivityPostImage.setImageBitmap(BitmapOperations.byteArrayToBitMap(post.image))


        //For back button

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.detailsMenuDeleteButton -> {
                deletePost()

            }

            android.R.id.home -> {
                onBackPressed()
            }
        }
        return true
    }

    override fun deletePost() {
        presenter.handleDeleteDatabaseOperation(post)
        presenter.clearActivitiesAndGoHome()
    }

    override fun getViewContext(): Context {
        return this
    }
}