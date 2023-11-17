package com.erdemserhat.conceptor.ui.main.adapters

import androidx.recyclerview.widget.RecyclerView

object AdapterOperations {
    private  var adapter:PostsAdapter? = null


    fun setAdapter(postsAdapter: PostsAdapter){
        if(adapter==null){
            adapter=postsAdapter
        }
    }

    fun notifyDataSet(){
        adapter?.notifyDataSetChanged()

    }
}