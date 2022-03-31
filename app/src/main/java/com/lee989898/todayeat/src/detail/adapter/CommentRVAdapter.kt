package com.lee989898.todayeat.src.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lee989898.todayeat.R

class CommentRVAdapter(val items: ArrayList<String>) :
    RecyclerView.Adapter<CommentRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentRVAdapter.ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.comment_list_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: CommentRVAdapter.ViewHolder, position: Int) {
        holder.bindItems(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(item: String) {
            val name = itemView.findViewById<TextView>(R.id.comment_list_name_tv)
            val content = itemView.findViewById<TextView>(R.id.comment_list_content_tv)
            val date = itemView.findViewById<TextView>(R.id.comment_list_date_tv)

        // 나중에 서버랑 연결
//            name = item.
        }
    }
}