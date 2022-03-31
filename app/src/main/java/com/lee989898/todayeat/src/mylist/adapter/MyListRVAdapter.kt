package com.lee989898.todayeat.src.mylist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lee989898.todayeat.R

class MyListRVAdapter(val items : ArrayList<String>): RecyclerView.Adapter<MyListRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyListRVAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.my_like_list_item_list, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyListRVAdapter.ViewHolder, position: Int) {
        holder.bindItems(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bindItems(item: String){

        }

    }

}