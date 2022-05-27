package com.lee989898.todayeat.src.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lee989898.todayeat.R
import com.lee989898.todayeat.databinding.CommentListItemBinding
import com.lee989898.todayeat.src.detail.model.review.ReviewData
import com.lee989898.todayeat.src.search.adapter.SearchData

class CommentRVAdapter : RecyclerView.Adapter<CommentRVAdapter.ViewHolder>() {

    private val _data = mutableListOf<ReviewData>()
    var data: List<ReviewData> = _data
        set(value) {
            _data.clear()
            _data.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CommentListItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(_data[position])
    }

    override fun getItemCount(): Int {
        return _data.size
    }

    class ViewHolder(private val binding: CommentListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindItems(item: ReviewData) {

            binding.commentListNameTv.text = item.userName
            binding.commentListContentTv.text = item.content
            Glide.with(binding.sivImage)
                .load(item.image)
                .into(binding.sivImage)
        }
    }
}