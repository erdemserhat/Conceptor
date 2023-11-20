package com.erdemserhat.conceptor.ui.main.presenter

import com.erdemserhat.conceptor.ui.main.view.MainMVPView

interface MainMVPPresenter {
    fun attachView(view: MainMVPView)
    fun initializeDatabase()
}