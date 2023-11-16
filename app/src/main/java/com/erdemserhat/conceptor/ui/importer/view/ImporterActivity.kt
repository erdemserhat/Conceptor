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

class ImporterActivity: BaseActivity<AddConceptAcitivityBinding> () {

    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var permissionLauncher: ActivityResultLauncher<String>
    var selectedBitmap : Bitmap? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        registerLauncher()
    }

    override fun getViewBinding(): AddConceptAcitivityBinding {
        return AddConceptAcitivityBinding.inflate(layoutInflater)

    }


    fun uploadImage(view: View){


    }

    fun saveImage(view: View){
        //Permission Control

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.TIRAMISU){
            //Android 33+ -> READ_MEDIA_IMAGES
            if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED){
                //user does not give permission

                if(ActivityCompat.shouldShowRequestPermissionRationale(this,android.Manifest.permission.READ_MEDIA_IMAGES)){
                    //rationale (snackbar)
                    Snackbar.make(view,"Permission needed for gallery",Snackbar.LENGTH_INDEFINITE).setAction("Give Permission",View.OnClickListener {
                        //request permission
                        permissionLauncher.launch(android.Manifest.permission.READ_MEDIA_IMAGES)



                    }).show()

                }else{
                    //request permissions
                    permissionLauncher.launch(android.Manifest.permission.READ_MEDIA_IMAGES)
                }



            }else{
                //User gave the permission
                val intentToGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentToGallery)
                binding.addConceptActivityImageView.setImageBitmap(selectedBitmap)

            }


        }else{
            //Android 32- -> READ_EXTERNAL_STORAGE
            if (ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                //user does not give permission

                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE
                    )
                ) {
                    //rationale (snackbar)
                    Snackbar.make(view, "Permission needed for gallery", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Give Permission", View.OnClickListener {
                            //request permission
                            permissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)


                        }).show()

                } else {
                    //request permissions
                    permissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                }


            } else {
                //User gave the permission
                val intentToGallery =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentToGallery)


            }


        }


    }

    private fun registerLauncher(){
        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {result->
            if(result.resultCode == RESULT_OK){
                val intentFromResult = result.data
                if(intentFromResult!=null) {
                    val imageData = intentFromResult.data
                    // binding.addConceptActivityImageView.setImageURI(imageData)
                    //Bitmap operations
                    if (imageData != null) {
                        try {
                            if(Build.VERSION.SDK_INT>=28) {
                                val source = ImageDecoder.createSource(this@ImporterActivity.contentResolver, imageData)
                                selectedBitmap = ImageDecoder.decodeBitmap(source)

                            }else{
                                selectedBitmap = MediaStore.Images.Media.getBitmap(contentResolver,imageData)
                            }

                            selectedBitmap?.let {
                                binding.addConceptActivityImageView.setImageBitmap(it)
                            }


                        } catch (e: Exception) {
                            e.printStackTrace()

                        }

                    }
                }

            }


        }

        permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){result->
            if(result){
                //permission granted
                val intentToGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentToGallery)
            }else{
                //permission denied
                Toast.makeText(ImporterActivity@this,"Permission needed",Toast.LENGTH_SHORT).show()
            }

        }

    }


}

