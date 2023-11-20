package com.erdemserhat.conceptor.ui.main.presenter

import com.erdemserhat.conceptor.data.database.AppDatabase
import com.erdemserhat.conceptor.ui.main.view.MainMVPView

class MainPresenter : MainMVPPresenter {
    private lateinit var view: MainMVPView
    override fun attachView(view: MainMVPView) {
        this.view=view

    }

    override fun initializeDatabase() {
        val database:AppDatabase = AppDatabase(view.getViewContext())
        database.readPosts()
    }
}