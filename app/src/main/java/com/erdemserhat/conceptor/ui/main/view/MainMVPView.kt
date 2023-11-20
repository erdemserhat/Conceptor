package com.erdemserhat.conceptor.ui.main.view

import android.content.Context

interface MainMVPView {
    fun getViewContext(): Context
    fun showPostList()
}