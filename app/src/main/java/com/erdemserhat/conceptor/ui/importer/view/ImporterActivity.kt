package com.erdemserhat.conceptor.ui.importer.view

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.erdemserhat.conceptor.databinding.AddConceptAcitivityBinding
import com.erdemserhat.conceptor.ui.base.view.BaseActivity
import com.google.android.material.snackbar.Snackbar

class ImporterActivity : BaseActivity<AddConceptAcitivityBinding>() {

    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var permissionLauncher: ActivityResultLauncher<String>
    private var selectedBitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerLauncher()
    }

    override fun getViewBinding(): AddConceptAcitivityBinding {
        return AddConceptAcitivityBinding.inflate(layoutInflater)
    }

    fun uploadImage(view: View) {
        // TODO: Implement image upload logic
    }

    fun saveImage(view: View) {
        if (hasStoragePermission()) {
            openGallery()
        } else {
            requestStoragePermission()
        }
    }

    private fun hasStoragePermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED
        } else {
            ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
        }
    }

    private fun requestStoragePermission() {
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

    private fun showPermissionSnackbar(permission: String) {
        Snackbar.make(
            findViewById(android.R.id.content),
            "Permission needed for gallery",
            Snackbar.LENGTH_INDEFINITE
        ).setAction("Give Permission") {
            permissionLauncher.launch(permission)
        }.show()
    }

    private fun openGallery() {
        val intentToGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        activityResultLauncher.launch(intentToGallery)
    }

    private fun handleImageSelectionResult(data: Intent?) {
        val imageData = data?.data
        if (imageData != null) {
            try {
                selectedBitmap = if (Build.VERSION.SDK_INT >= 28) {
                    val source = ImageDecoder.createSource(contentResolver, imageData)
                    ImageDecoder.decodeBitmap(source)
                } else {
                    MediaStore.Images.Media.getBitmap(contentResolver, imageData)
                }
                selectedBitmap?.let {
                    binding.addConceptActivityImageView.setImageBitmap(it)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun registerLauncher() {
        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    handleImageSelectionResult(result.data)
                }
            }

        permissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
                if (result) {
                    openGallery()
                } else {
                    Toast.makeText(this@ImporterActivity, "Permission needed", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
