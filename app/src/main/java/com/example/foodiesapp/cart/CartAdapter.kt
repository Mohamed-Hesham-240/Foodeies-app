package com.example.foodiesapp.cart

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodiesapp.FoodAdapter
import com.example.foodiesapp.Product
import com.example.foodiesapp.R

class CartAdapter(private val cartList: ArrayList<Product>) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {


    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemPriceTextView: TextView = itemView.findViewById(R.id.price)
        val itemNameTextView: TextView = itemView.findViewById(R.id.name)
        val itemQuantityTextView: TextView = itemView.findViewById(R.id.Quantity)
        val itemImage: ImageView = itemView.findViewById(R.id.foodPic)

    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val product = cartList[position]
        holder.itemNameTextView.text = product.name
        holder.itemQuantityTextView.text = product.bought_items_count.toString()
        holder.itemPriceTextView.text = "" + product.bought_items_count.toDouble() * product.price
        holder.itemImage.setImageBitmap(product.image)
    }

    override fun getItemCount(): Int {
        return cartList.size;
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.activity_cart_itemm, parent, false)
        return CartAdapter.CartViewHolder(view)

    }


}