package com.example.foodiesapp

import Product
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.foodiesapp.cart.Cart
import com.example.foodiesapp.login.RegisterActivity

class ProductsActivity : AppCompatActivity() {
    var currentItem:Int = 0
    lateinit var products :ArrayList<Product>
    lateinit var foodListFragment:FoodListFragment
    lateinit var itemDescriptionFragment:ItemDescriptionFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
        foodListFragment = (supportFragmentManager.findFragmentById(R.id.list_container)) as FoodListFragment
        itemDescriptionFragment= (supportFragmentManager.findFragmentById(R.id.description_container)) as ItemDescriptionFragment
        products = foodListFragment.products
        updateDescriptionFragment()

    }
    fun updateDescriptionFragment(index: Int = 0){
        itemDescriptionFragment.descriptionTextView.text = products[index].description
        itemDescriptionFragment.nameTextView.text = products[index].name
        itemDescriptionFragment.priceTextView.text = products[index].price.toString()
        itemDescriptionFragment.quantityTextView.text = products[index].bought_items_count.toString()
        itemDescriptionFragment.image.setImageBitmap(products[index].image)
    }

    companion object{
        var cartProducts : ArrayList<Product> = ArrayList(0)
    }

    fun goToCart(){
        cartProducts.clear()
        for (product in products){
            if (product.bought_items_count != 0)
                cartProducts.add(product)
        }
        val intent = Intent(this, Cart::class.java)
        startActivity(intent)

    }



}