package com.example.foodiesapp.cart

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodiesapp.Product
import com.example.foodiesapp.ProductsActivity
import com.example.foodiesapp.R

class Cart : AppCompatActivity() {
    lateinit var totalTextView: TextView
    lateinit var cartProducts : ArrayList<Product>
    lateinit var checkoutBtn : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        //
        val img1 : Bitmap = BitmapFactory.decodeResource(resources, R.drawable.dish1)
        val img2 : Bitmap = BitmapFactory.decodeResource(resources, R.drawable.dish3)
        val bundle: Bundle? = intent.extras
        cartProducts = ProductsActivity.cartProducts
    //

        val cartList: RecyclerView = findViewById(R.id.rv_cart)
        val layoutManager = LinearLayoutManager(this)
        val adapter = CartAdapter(cartProducts)
        cartList.adapter = adapter
        cartList.layoutManager = layoutManager
        totalTextView = findViewById(R.id.Total)
        totalTextView.text = "" + calcTotal()
        checkoutBtn = findViewById(R.id.btn_checkout)
        checkoutBtn.setOnClickListener {
            Toast.makeText(
                this@Cart,
                "You have been charged with ${totalTextView.text}",
                Toast.LENGTH_LONG
            ).show()
        }

    }

    private fun calcTotal(): Double{
        var answer : Double = 0.0
        for (product in cartProducts)
            answer += product.price * product.bought_items_count.toDouble()
        return answer
    }

}