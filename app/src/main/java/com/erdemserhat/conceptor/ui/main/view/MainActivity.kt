package com.erdemserhat.conceptor.ui.main.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.erdemserhat.conceptor.data.database.AppDatabase
import com.erdemserhat.conceptor.databinding.MainActivityBinding
import com.erdemserhat.conceptor.ui.base.view.BaseActivity
import com.erdemserhat.conceptor.ui.main.adapters.AdapterOperations
import com.erdemserhat.conceptor.ui.main.adapters.PostsAdapter
import com.erdemserhat.conceptor.ui.main.presenter.MainContract
import com.erdemserhat.conceptor.ui.main.presenter.MainPresenter

class MainActivity : BaseActivity<MainActivityBinding> (), MainContract.View {
    //dependency injection
    private lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Attach presenter to view
        presenter = MainPresenter()
        presenter.attachView(this)


        //Adapter Binding
        val adapter = PostsAdapter()
        binding.mainActivityRecyclerView.adapter=adapter
        binding.mainActivityRecyclerView.layoutManager = LinearLayoutManager(this)
        AdapterOperations.setAdapter(adapter)

        //Prepare List
        showPostList()



    }
    //View Binding
    override fun getViewBinding(): MainActivityBinding {
        return MainActivityBinding.inflate(layoutInflater)
    }


    override fun getViewContext(): Context {
        return this
    }

    override fun showPostList() {
        presenter.initializeDatabase()
    }


}