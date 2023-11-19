package com.erdemserhat.conceptor.ui.detailer.presenter

import com.erdemserhat.conceptor.data.database.repository.datamodel.Posts
import com.erdemserhat.conceptor.ui.detailer.view.DetailerMVPView

interface DetailerMVPPresenter {
    fun handleDeleteDatabaseOperation(post: Posts)
    fun attachView(view: DetailerMVPView)
    fun detachView()

    fun clearActivitiesAndGoHome()

}