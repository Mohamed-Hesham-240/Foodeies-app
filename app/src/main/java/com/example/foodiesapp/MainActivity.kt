package com.example.foodiesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.foodiesapp.cart.Cart
import com.example.foodiesapp.login.RegisterActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActivityLink()

        val btnLogin = findViewById<Button>(R.id.btn_login)

        //go to cart activity
        btnLogin.setOnClickListener {
            val intent = Intent(this,ProductsActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


    //go to register activity
    private fun setupActivityLink() {
        val linkTextView = findViewById<TextView>(R.id.register)
        linkTextView.setOnClickListener {
            val switchActivityIntent = Intent(this, RegisterActivity::class.java)
            startActivity(switchActivityIntent)
        }

    }
}
