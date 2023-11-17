package com.erdemserhat.conceptor.ui.main.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.erdemserhat.conceptor.data.database.AppDatabase
import com.erdemserhat.conceptor.databinding.MainActivityBinding
import com.erdemserhat.conceptor.ui.base.view.BaseActivity
import com.erdemserhat.conceptor.ui.main.adapters.PostsAdapter

class MainActivity : BaseActivity<MainActivityBinding> () {
    override fun getViewBinding(): MainActivityBinding {
        return MainActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appDatabase = AppDatabase(this)
        appDatabase.readPosts()
        val x = 5
        Log.d("TAG", "x değeri: $x")

        val adapter = PostsAdapter()
        binding.mainActivityRecyclerView.adapter=adapter
        binding.mainActivityRecyclerView.layoutManager = LinearLayoutManager(this)



    }


}