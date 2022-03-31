package com.lee989898.todayeat.src.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lee989898.todayeat.R

class SearchRVAdapter(val items : ArrayList<String>): RecyclerView.Adapter<SearchRVAdapter.ViewHolder>() {

    interface ItemCLick{
        fun onClick(view: View, position: Int)
    }
    var itemClick: ItemCLick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchRVAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.search_list_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: SearchRVAdapter.ViewHolder, position: Int) {

        if(itemClick != null){
            holder.itemView.setOnClickListener { v->
                itemClick?.onClick(v, position)
            }
        }

        holder.bindItems(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bindItems(item: String){
            val image = itemView.findViewById<ImageView>(R.id.search_item_iv)
            val name = itemView.findViewById<TextView>(R.id.search_item_tv)
            val heartOff = itemView.findViewById<ImageView>(R.id.search_heart_off_iv)
            val heartOn = itemView.findViewById<ImageView>(R.id.search_heart_on_iv)

            // 나중에 서버랑 연결
//            image = item.
        }

    }

}