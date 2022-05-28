package com.lee989898.todayeat.src.mylike.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lee989898.todayeat.R
import com.lee989898.todayeat.databinding.MyLikeListItemListBinding
import com.lee989898.todayeat.src.search.adapter.SearchData

class LikeRVAdapter: RecyclerView.Adapter<LikeRVAdapter.ViewHolder>() {

    private val _data = mutableListOf<LikeData>()
    var data: List<LikeData> = _data
        set(value) {
            _data.clear()
            _data.addAll(value)
            notifyDataSetChanged()
        }

    interface ItemCLick {
        fun onClick(view: View, likeData: LikeData)
    }

    var itemClick: ItemCLick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MyLikeListItemListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(_data[position])
    }

    override fun getItemCount(): Int {
        return _data.size
    }

    inner class ViewHolder(private val binding: MyLikeListItemListBinding): RecyclerView.ViewHolder(binding.root){

        fun bindItems(item: LikeData){
            binding.myLikeItemTv.text = item.name
            Glide.with(binding.myLikeItemIv)
                .load(item.image)
                .into(binding.myLikeItemIv)
        }

    }

}