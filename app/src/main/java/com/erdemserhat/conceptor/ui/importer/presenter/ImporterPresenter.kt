package com.erdemserhat.conceptor.ui.importer.presenter

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.provider.MediaStore
import androidx.core.content.ContextCompat
import com.erdemserhat.conceptor.data.database.AppDatabase
import com.erdemserhat.conceptor.data.database.repository.datamodel.Posts
import com.erdemserhat.conceptor.ui.importer.view.ImporterMVPView
import com.erdemserhat.conceptor.ui.main.view.MainActivity

class ImporterPresenter : ImporterMVPPresenter {

    private var view: ImporterMVPView? = null


    override fun handleImageSelection(data: Intent?) {
        val selectedBitmap: Bitmap
        val imageData = data?.data
        if (imageData != null) {
            try {
                selectedBitmap = if (Build.VERSION.SDK_INT >= 28) {
                    val source = view?.getViewContext()?.contentResolver?.let {
                        ImageDecoder.createSource(
                            it, imageData
                        )
                    }
                    ImageDecoder.decodeBitmap(source!!)
                } else {
                    MediaStore.Images.Media.getBitmap(
                        view?.getViewContext()?.contentResolver,
                        imageData
                    )
                }
                selectedBitmap?.let {
                    view?.showSelectedImage(it)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }


        }


    }

    override fun handlePermissionResult(isGranted: Boolean) {
        if (isGranted) {
            view?.showSelectedImage(null)
        } else {
            view?.showPermissionNeededMessage()
        }
    }

    override fun attachView(view: ImporterMVPView) {
        this.view = view
    }

    override fun savePost(post:Posts) {
        val appDatabase: AppDatabase = AppDatabase(view!!.getViewContext())
        appDatabase.insertPost(post)
        appDatabase.readPosts()
        val intent :Intent =Intent(view!!.getViewContext(),MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)///-----> Important
        view!!.getViewContext().startActivity(intent)

    }

    override fun detachView() {
        this.view=null
    }

    override fun hasStoragePermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            view?.let {
                ContextCompat.checkSelfPermission(
                    it.getViewContext(),
                    android.Manifest.permission.READ_MEDIA_IMAGES
                )
            } == PackageManager.PERMISSION_GRANTED
        } else {
            view?.let {
                ContextCompat.checkSelfPermission(
                    it.getViewContext(),
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                )
            } == PackageManager.PERMISSION_GRANTED
        }
    }
}
