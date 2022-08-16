package com.example.foodiesapp

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
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

        btnLogin.setOnClickListener {
            val intent = Intent(this,Cart::class.java)
            startActivity(intent)
        }
    }


    fun setupActivityLink() {
        val linkTextView = findViewById<TextView>(R.id.register)
        linkTextView.setOnClickListener {
            val switchActivityIntent = Intent(this, RegisterActivity::class.java)
            startActivity(switchActivityIntent)
        }

    }
}
