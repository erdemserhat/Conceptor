package com.erdemserhat.conceptor.ui.detailer.presenter

import android.content.Context
import com.erdemserhat.conceptor.data.database.repository.datamodel.Posts
import com.erdemserhat.conceptor.ui.base.presenter.BaseMVPPresenter
import com.erdemserhat.conceptor.ui.base.presenter.BasePresenter
import com.erdemserhat.conceptor.ui.base.view.BaseMVPView
import com.erdemserhat.conceptor.ui.detailer.view.DetailerMVPView

interface DetailerMVPPresenter: BaseMVPPresenter<DetailerMVPView> {
    fun handleDeleteDatabaseOperation(post: Posts)

    fun clearActivitiesAndGoHome()


}