package com.example.foodiesapp.cart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodiesapp.R

class Cart : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView //for setting up recycle view
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        setUpRecyclerView()
        var Result = findViewById<TextView>(R.id.Total)


       val cart= CartAdapter ()
        Result.text="Total ${cart.finaltotal} $"
        Log.d("###","totallllllll ${cart.finaltotal}")

    }
    private fun setUpRecyclerView() {
        recyclerView=findViewById(R.id.rv_cart)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.adapter= CartAdapter()

}

}