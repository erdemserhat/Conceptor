package com.erdemserhat.conceptor.ui.main.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.erdemserhat.conceptor.data.database.repository.dataset.PostsDataset
import com.erdemserhat.conceptor.databinding.RecyclerviewPostRowBinding
import com.erdemserhat.conceptor.ui.detailer.view.DetailerActivity

class PostsAdapter(): RecyclerView.Adapter<PostsAdapter.PostHolder>() {

    class PostHolder(val binding:RecyclerviewPostRowBinding):RecyclerView.ViewHolder(binding.root){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val binding:RecyclerviewPostRowBinding = RecyclerviewPostRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PostHolder(binding)

    }

    override fun getItemCount(): Int {
        return PostsDataset.postList.size

    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {

        holder.binding.recyclerViewPostRowTitle.text= PostsDataset.postList[position].title
        holder.binding.recyclerViewPostRowDescription.text= PostsDataset.postList[position].transcription

        holder.itemView.setOnClickListener {
            val intent: Intent = Intent(holder.itemView.context,DetailerActivity::class.java)
            intent.putExtra("post", PostsDataset.postList[position])
            holder.itemView.context.startActivity(intent)
        }
    }


}