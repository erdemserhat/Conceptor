package com.erdemserhat.conceptor.ui.base.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding> :AppCompatActivity() {
    lateinit var binding:VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //View binding
        binding = getViewBinding()
        val view = binding.root
        setContentView(view)

    }

    abstract fun getViewBinding(): VB

}