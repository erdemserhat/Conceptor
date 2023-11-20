package com.erdemserhat.conceptor.ui.base.presenter

import com.erdemserhat.conceptor.ui.base.view.BaseMVPView

interface BaseMVPPresenter < V : BaseMVPView> {

    fun attachView(view: V)
    fun detachView()

}