package com.lee989898.todayeat.src.survey.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.lee989898.todayeat.R
import com.lee989898.todayeat.src.fooddetail.FoodDetailActivity
import com.lee989898.todayeat.src.recommendFoodList.listActivity
import com.lee989898.todayeat.src.survey.model.FoodData

class FoodAdapter (
    private val context: Context, private val dataset:ArrayList<FoodData>
): RecyclerView.Adapter<FoodAdapter.ItemViewHolder>(){
    class ItemViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val textView1: TextView = view.findViewById(R.id.item_title)
        val imageView: ImageView = view.findViewById(R.id.item_image)
        val textView2: TextView = view.findViewById(R.id.item_name)
    }

    //각 아이템 클릭 리스너 설정
    private lateinit var itemClickListener : OnItemClickListener
    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.recommend_food_item_list, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: FoodAdapter.ItemViewHolder, position: Int) {
        val item = dataset[position]

        //데이터 적용
        holder.textView2.text = item.name
//        holder.imageView.setImageURI(item.image.toUri())

        var tagStr = ""
        for (tag in item.tagList) {
            tagStr += "#" + tag.name + " "
        }
        holder.textView1.text = tagStr

        holder.view.setOnClickListener {
            var intent = Intent(context, FoodDetailActivity::class.java)
            intent.putExtra("foodId", dataset[position].foodId)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

}