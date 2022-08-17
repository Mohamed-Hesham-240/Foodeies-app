package com.example.foodiesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.foodiesapp.cart.Cart
import com.example.foodiesapp.login.RegisterActivity

class MainActivity : AppCompatActivity() {
    var email: EditText?=null
    var pass: EditText?=null
    lateinit  var code :String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActivityLink()
        email= findViewById<EditText>(R.id.et_email)
        pass= findViewById<EditText>(R.id.et_password)
        val btnLogin = findViewById<Button>(R.id.btn_login)
        //go to cart activity
        btnLogin.setOnClickListener {
            makeRequest()
            Log.d("f#","${code}")
            if (code=="200")
            {
                val intent = Intent(this, ProductsActivity::class.java)
                startActivity(intent)
            }
            else  {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                Log.d("f#","${code}")
            }




    //go to register activity
    private fun setupActivityLink() {
        val linkTextView = findViewById<TextView>(R.id.register)
        linkTextView.setOnClickListener {
            val switchActivityIntent = Intent(this, RegisterActivity::class.java)
            startActivity(switchActivityIntent)
        }

    private fun makeRequest()
    {

        val api = Retrofit.Builder()
            .baseUrl("https://murmuring-temple-54993.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(userInterface::class.java)

        val requestModel = userRequset( email?.text.toString(),pass?.text.toString())

        api.login( email?.text.toString(),pass?.text.toString())?.enqueue(
            object : Callback<responseLogin> {
                override fun onResponse(
                    call: Call<responseLogin>,
                    response: Response<responseLogin>
                ) {
                    Log.d("##", "${response.body()?.token}")
                    code=response.code().toString()
                }

                override fun onFailure(call: Call<responseLogin>, t: Throwable) {
                    Toast.makeText(this@MainActivity,"can't login", Toast.LENGTH_LONG).show()
                }

            }
        )

    }
}
