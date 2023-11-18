package com.erdemserhat.conceptor.ui.main.presenter

import com.erdemserhat.conceptor.data.database.AppDatabase

class MainPresenter : MainContract.Presenter {
    private lateinit var view: MainContract.View
    override fun attachView(view: MainContract.View) {
        this.view=view

    }

    override fun initializeDatabase() {
        val database:AppDatabase = AppDatabase(view.getViewContext())
        database.readPosts()
    }
}