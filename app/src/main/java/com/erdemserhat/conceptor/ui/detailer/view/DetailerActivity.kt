package com.erdemserhat.conceptor.ui.detailer.view

import com.erdemserhat.conceptor.databinding.DetailsActivityBinding
import com.erdemserhat.conceptor.ui.base.view.BaseActivity

class DetailerActivity: BaseActivity<DetailsActivityBinding> () {
    override fun getViewBinding(): DetailsActivityBinding {
        return DetailsActivityBinding.inflate(layoutInflater)
    }
}