package com.erdemserhat.conceptor.ui.base.presenter

import android.content.Context
import com.erdemserhat.conceptor.ui.base.view.BaseMVPView

abstract class BasePresenter<V : BaseMVPView> :BaseMVPPresenter<V> {
    //Inject
    public var view: V? = null
    override fun attachView(view: V?) {
        this.view=view
    }

    override fun detachView() {
        view = null
    }
}