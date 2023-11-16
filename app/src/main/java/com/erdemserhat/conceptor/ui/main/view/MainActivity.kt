package com.erdemserhat.conceptor.ui.main.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.erdemserhat.conceptor.databinding.MainActivityBinding
import com.erdemserhat.conceptor.ui.base.view.BaseActivity

class MainActivity : BaseActivity<MainActivityBinding> () {
    override fun getViewBinding(): MainActivityBinding {
        return MainActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


}