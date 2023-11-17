package com.erdemserhat.conceptor.ui.importer.view

import ImporterContract
import ImporterPresenter
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.erdemserhat.conceptor.data.database.repository.posts.Posts
import com.erdemserhat.conceptor.databinding.AddConceptAcitivityBinding
import com.erdemserhat.conceptor.ui.base.view.BaseActivity
import com.erdemserhat.conceptor.utils.AppConstants
import com.google.android.material.snackbar.Snackbar
import java.sql.Blob

class ImporterActivity : BaseActivity<AddConceptAcitivityBinding>(), ImporterContract.View {

    private lateinit var presenter: ImporterContract.Presenter
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var permissionLauncher: ActivityResultLauncher<String>
    private lateinit var selectedBitmap: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = ImporterPresenter()
        presenter.attachView(this) // Presenter'ı View'a bağla
        registerLauncher()

    }


    fun saveImage(view: View) {
        if (presenter.hasStoragePermission()) {
            openToGallery()
        } else {
            requestStoragePermission()
        }

    }


    fun saveConcept(view:View){
        val conceptTitle:String = binding.addConceptActivityEditTextTitle.text.toString()
        val conceptTranscription:String = binding.addConceptActivityEditTextPostDescription.text.toString()
        val conceptImageBitmap = AppConstants.makeSmallerBitmap(selectedBitmap,300)
        var blobImage = AppConstants.bitMapToBlob(conceptImageBitmap)
        println(blobImage.size)
        val post: Posts = Posts(conceptTitle,conceptTranscription,blobImage)
        presenter.savePost(post)

    }


    override fun showSelectedImage(bitmap: Bitmap?) {

        binding.addConceptActivityImageView.setImageBitmap(bitmap)
        selectedBitmap=bitmap!!
        println(AppConstants.bitMapToBlob(selectedBitmap).size)
    }

    override fun showPermissionNeededMessage() {
        Toast.makeText(this@ImporterActivity, "Permission needed", Toast.LENGTH_SHORT).show()
    }


    override fun requestStoragePermission() {
        val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            android.Manifest.permission.READ_MEDIA_IMAGES
        } else {
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        }

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
            showPermissionSnackbar(permission)
        } else {
            permissionLauncher.launch(permission)
        }
    }


    override fun getViewContext(): Context {
        return this!!
    }

    override fun showPermissionSnackbar(permission: String) {
        Snackbar.make(
            findViewById(android.R.id.content),
            "Permission needed for gallery",
            Snackbar.LENGTH_INDEFINITE
        ).setAction("Give Permission") {
            permissionLauncher.launch(permission)
        }.show()
    }

    override fun openToGallery() {
        val intentToGallery =
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        activityResultLauncher.launch(intentToGallery)
    }


    private fun registerLauncher() {
        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    presenter.handleImageSelection(result.data)
                }
            }

        permissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
                if (result) {
                    openToGallery()
                } else {
                    showPermissionNeededMessage()


                }
            }
    }

    override fun getViewBinding(): AddConceptAcitivityBinding {
        return AddConceptAcitivityBinding.inflate(layoutInflater)
    }
}
