package com.example.foodiesapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.foodiesapp.cart.Cart
import com.example.foodiesapp.login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActivityLink()

        val btnLogin = findViewById<Button>(R.id.btn_login)
makeRequest()
        //go to cart activity
        btnLogin.setOnClickListener {
            val intent = Intent(this,Cart::class.java)
            startActivity(intent)
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

    private fun makeRequest()
    {
        val api = Retrofit.Builder()
            .baseUrl("https://course-product-gallery.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(userInterface::class.java)

        val requestModel = User("bye", "hello123","123")

        api.login(requestModel)?.enqueue(
            object : Callback<responseLogin> {
                override fun onResponse(
                    call: Call<responseLogin>,
                    response: Response<responseLogin>
                ) {
                    Toast.makeText(this@MainActivity,"login done !", Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<responseLogin>, t: Throwable) {
                    Toast.makeText(this@MainActivity,"can't login", Toast.LENGTH_LONG).show()
                }

            }
        )
    }
}
