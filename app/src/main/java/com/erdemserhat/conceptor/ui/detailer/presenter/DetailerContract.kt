package com.erdemserhat.conceptor.ui.detailer.presenter

import android.content.Context
import com.erdemserhat.conceptor.data.database.repository.datamodel.Posts

interface DetailerContract {

    interface View{
        fun deletePost()

        fun getViewContext(): Context

    }

    interface Presenter{

        fun handleDeleteDatabaseOperation(post: Posts)
        fun attachView(view:View)
        fun detachView()

    }
}