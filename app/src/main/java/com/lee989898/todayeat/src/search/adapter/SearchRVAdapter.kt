package com.lee989898.todayeat.src.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lee989898.todayeat.R
import com.lee989898.todayeat.databinding.SearchListItemBinding

class SearchRVAdapter : RecyclerView.Adapter<SearchRVAdapter.ViewHolder>() {

    private val _data = mutableListOf<SearchData>()
    var data: List<SearchData> = _data
        set(value) {
            _data.clear()
            _data.addAll(value)
            notifyDataSetChanged()
        }



    interface ItemCLick {
        fun onClick(view: View, searchData: SearchData)
    }

    var itemClick: ItemCLick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SearchListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (itemClick != null) {
            holder.itemView.setOnClickListener { v ->
                itemClick?.onClick(v, _data[position])
            }
        }

        holder.bindItems(_data[position])
    }

    override fun getItemCount(): Int {
        return _data.size
    }

    class ViewHolder(private val binding: SearchListItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bindItems(item: SearchData) {
            binding.searchNameTv.text = item.name
            Glide.with(binding.searchItemIv)
                .load(item.image)
                .into(binding.searchItemIv)
            for(i in 0 until item.tag.size){
                binding.searchFastFoodTv.append("#" + item.tag[i] + " ")
            }
        }
    }
}