package com.lee989898.todayeat.src.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lee989898.todayeat.R

class HomeRVAdapter(val items : ArrayList<String>): RecyclerView.Adapter<HomeRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.ranking_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bindItems(item: String){
//            val image = itemView.findViewById<ImageView>(R.id.)
//            val name = itemView.findViewById<TextView>(R.id.)
//            val heartOff = itemView.findViewById<ImageView>(R.id.)
//            val heartOn = itemView.findViewById<ImageView>(R.id.)

            // 나중에 서버랑 연결
//            image = item.
        }

    }

}