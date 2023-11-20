package com.erdemserhat.conceptor.ui.importer.presenter

import android.content.Intent
import com.erdemserhat.conceptor.data.database.repository.datamodel.Posts
import com.erdemserhat.conceptor.ui.importer.view.ImporterMVPView

interface ImporterMVPPresenter {

    fun handleImageSelection(data: Intent?)
    fun handlePermissionResult(isGranted: Boolean)
    fun attachView(view: ImporterMVPView)
    fun savePost(post: Posts)
    fun detachView()
    fun hasStoragePermission(): Boolean
}