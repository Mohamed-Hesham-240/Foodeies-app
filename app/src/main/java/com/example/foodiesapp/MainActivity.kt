package com.example.foodiesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.foodiesapp.cart.Cart
import com.example.foodiesapp.login.RegisterActivity
import com.example.foodiesapp.login.responseLogin
import com.example.foodiesapp.login.userInterface
import com.example.foodiesapp.login.userRequset
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    var email: EditText? = null
    var pass: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActivityLink()
        email = findViewById<EditText>(R.id.et_email)
        pass = findViewById<EditText>(R.id.et_password)
        val btnLogin = findViewById<Button>(R.id.btn_login)
        //go to cart activity


        btnLogin.setOnClickListener {
            makeRequest()

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

    fun makeRequest() {

        val api = Retrofit.Builder()
            .baseUrl("https://murmuring-temple-54993.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(userInterface::class.java)

        val requestModel = userRequset(email?.text.toString(), pass?.text.toString())

        api.login(email?.text.toString(), pass?.text.toString())?.enqueue(
            object : Callback<responseLogin> {
                override fun onResponse(
                    call: Call<responseLogin>,
                    response: Response<responseLogin>
                ) {
                //  response.body()?.access_token.toString()
                  //  Log.d("##", respCode)
                    if (response.code().toString()=="200")
                    {
                        val intent = Intent(this@MainActivity, ProductsActivity::class.java)
                        startActivity(intent)
                        Toast.makeText(this@MainActivity, "logged in successfully !", Toast.LENGTH_LONG).show()

                    }

                    else
                    {
                        val intent = Intent(this@MainActivity, MainActivity::class.java)
                        startActivity(intent)
                        Toast.makeText(this@MainActivity, "something went wrong!", Toast.LENGTH_LONG).show()

                    }

                }

                override fun onFailure(call: Call<responseLogin>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "can't login", Toast.LENGTH_LONG).show()
                }

            }
        )

       // Log.d("##", respCode)
    }
}
