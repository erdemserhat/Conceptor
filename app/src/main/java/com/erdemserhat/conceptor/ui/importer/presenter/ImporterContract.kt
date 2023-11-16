package com.erdemserhat.conceptor.ui.importer.presenter

import android.content.Intent
import android.graphics.Bitmap

interface ImporterContract {

    interface View {
        fun showSelectedImage(bitmap: Bitmap)
        fun showPermissionNeededMessage()
    }

    interface Presenter {
        fun checkPermissionAndLoadImage()
        fun handlePermissionResult(isGranted: Boolean)
        fun handleImageSelectionResult(data: Intent?)
        fun attachView(view: View)
        fun detachView()
    }
}