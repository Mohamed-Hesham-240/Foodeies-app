package com.example.foodiesapp.cart

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodiesapp.R

class CartAdapter: RecyclerView.Adapter<CartAdapter.cartViewHolder>() {
    var finaltotal=0.0

   inner class cartViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
       val itemPrice: TextView = itemView.findViewById(R.id.price)
       val itemName: TextView = itemView.findViewById(R.id.name)
       val itemQuanitity: TextView = itemView.findViewById(R.id.Quantity)
       val itemPicture: ImageView = itemView.findViewById(R.id.foodPic)


   }

    override fun onBindViewHolder(holder: cartViewHolder, position: Int) {
        holder.itemName.text = "Fast Food"
        holder.itemQuanitity.text = "1"
        holder.itemPrice.text ="200"

    }
    override fun getItemCount(): Int {
        return 3;
    }
    fun getTotal(holder: cartViewHolder) {
        var total=0.0
        for (i in 0..itemCount)
        {
          total+=holder.itemPrice.text.toString().toDouble()
        }
        finaltotal=total
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): cartViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.activity_cart_itemm, parent, false)
        getTotal(cartViewHolder(v))
        return cartViewHolder(v)

    }


}