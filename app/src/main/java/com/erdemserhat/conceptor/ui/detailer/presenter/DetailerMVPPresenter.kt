package com.erdemserhat.conceptor.ui.detailer.presenter

import android.content.Context
import com.erdemserhat.conceptor.data.database.repository.datamodel.Posts
import com.erdemserhat.conceptor.ui.base.view.BaseMVPVie
import com.erdemserhat.conceptor.ui.base.view.BaseMVPView
import com.erdemserhat.conceptor.ui.detailer.view.DetailerMVPView

interface DetailerMVPPresenter : BaseMVPView<Context>{
    fun handleDeleteDatabaseOperation(post: Posts)

    fun clearActivitiesAndGoHome()

}