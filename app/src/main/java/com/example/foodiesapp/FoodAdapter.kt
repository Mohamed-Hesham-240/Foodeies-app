package com.example.foodiesapp

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class FoodAdapter (private val foodList: ArrayList<Bitmap>)
    : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>(){
    class FoodViewHolder(view: View, listener: OnItemCLickListener) : RecyclerView.ViewHolder(view){
        val foodImageView : ImageView = view.findViewById(R.id.food_img_view)

            init{
                view.setOnClickListener {
                    listener.onItemCLick(adapterPosition)
                }
            }

    }

    private lateinit var mListener: OnItemCLickListener

    interface OnItemCLickListener{
        fun onItemCLick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemCLickListener){
        mListener = listener
    }


    override fun getItemCount(): Int {
        return foodList.size
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val foodBitMap = foodList[position]
        holder.foodImageView.setImageBitmap(foodBitMap)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.food_list_item, parent, false)
        return FoodViewHolder(view,mListener)
    }


}