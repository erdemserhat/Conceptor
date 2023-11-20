package com.erdemserhat.conceptor.ui.base.presenter

import android.content.Context
import com.erdemserhat.conceptor.ui.base.view.BaseMVPView

class BasePresenter():BaseMVPPresenter<BaseMVPView<Context>> {
    //Inject
    private var view: BaseMVPView<Context>? = null
    override fun attachView(view: BaseMVPView<Context>) {
        this.view=view
    }

    override fun detachView() {
        view=null
    }


}