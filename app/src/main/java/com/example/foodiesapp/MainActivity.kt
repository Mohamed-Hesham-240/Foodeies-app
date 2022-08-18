package com.example.foodiesapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.media.RouteDiscoveryPreference
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
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
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor



    private fun getToken():String{
        val sharedPreferences = getPreferences(Context.MODE_PRIVATE)
        return sharedPreferences.getString("token", "-1").toString()
    }

    companion object{
        lateinit var token:String
    }

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
            token = getToken()
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
            .baseUrl("https://course-product-gallery.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(userInterface::class.java)

        val requestModel = userRequset(email?.text.toString(), pass?.text.toString())

        api.login(requestModel)?.enqueue(
            object : Callback<responseLogin> {
                override fun onResponse(
                    call: Call<responseLogin>,
                    response: Response<responseLogin>
                ) {
                    sharedPreferences = getPreferences(Context.MODE_PRIVATE)
                    editor = sharedPreferences.edit()
                    editor.putString("token", response.body()?.access_token.toString()).commit()
                     Log.d("##",response.code().toString())
                    if (response.code().toString() == "200") {
                        val intent = Intent(this@MainActivity, ProductsActivity::class.java)
                        startActivity(intent)
                        Toast.makeText(
                            this@MainActivity,
                            "logged in successfully !",
                            Toast.LENGTH_LONG
                        ).show()

                    } else {
                        val intent = Intent(this@MainActivity, MainActivity::class.java)
                        startActivity(intent)
                        Toast.makeText(
                            this@MainActivity,
                            "something went wrong!",
                            Toast.LENGTH_LONG
                        ).show()

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
