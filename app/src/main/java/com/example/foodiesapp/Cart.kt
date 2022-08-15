package com.example.foodiesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Cart : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView //for setting up recycle view
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        setUpRecyclerView()
    }
    private fun setUpRecyclerView() {
        recyclerView=findViewById(R.id.rv_cart)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.adapter= CartAdapter()

}

}