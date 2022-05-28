package com.lee989898.todayeat.src.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lee989898.todayeat.R
import com.lee989898.todayeat.databinding.RankingItemBinding
import com.lee989898.todayeat.src.detail.model.review.ReviewData

class HomeRVAdapter: RecyclerView.Adapter<HomeRVAdapter.ViewHolder>() {

    private val _data = mutableListOf<String>()
    var data: List<String> = _data
        set(value) {
            _data.clear()
            _data.addAll(value)
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RankingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(_data[position])
    }

    override fun getItemCount(): Int {
        return _data.size
    }

    class ViewHolder(private val binding: RankingItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bindItems(item: String){

        }

    }

}