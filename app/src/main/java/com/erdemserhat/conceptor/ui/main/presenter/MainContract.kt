package com.erdemserhat.conceptor.ui.main.presenter

import android.content.Context

interface MainContract {
    interface View {
        fun getViewContext(): Context
        fun showPostList()


    }

    interface Presenter {
        fun attachView(view: View)
        fun initializeDatabase()


    }
}