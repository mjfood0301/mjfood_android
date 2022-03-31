package com.lee989898.todayeat.src.ranking.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lee989898.todayeat.R

class RankingRVAdapter(val items : ArrayList<String>): RecyclerView.Adapter<RankingRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingRVAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.ranking_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RankingRVAdapter.ViewHolder, position: Int) {
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