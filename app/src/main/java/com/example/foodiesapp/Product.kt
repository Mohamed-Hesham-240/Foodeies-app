package com.example.foodiesapp

import android.graphics.Bitmap
import java.io.Serializable

data class Product (
    val id: Int, val name: String, val price: Double, val image:Bitmap, val description: String,
    val is_added_to_cart: Boolean, var bought_items_count: Int )