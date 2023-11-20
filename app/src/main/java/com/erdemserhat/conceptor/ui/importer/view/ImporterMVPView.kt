package com.erdemserhat.conceptor.ui.importer.view

import android.content.Context
import android.graphics.Bitmap

interface ImporterMVPView {

    fun showSelectedImage(bitmap: Bitmap?)

    fun showPermissionNeededMessage()
    fun showPermissionSnackbar(permission: String)
    fun openToGallery()
    fun requestStoragePermission()

    fun getViewContext(): Context
}