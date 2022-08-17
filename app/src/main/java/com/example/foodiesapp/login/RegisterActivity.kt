package com.example.foodiesapp.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.foodiesapp.MainActivity
import com.example.foodiesapp.R
import com.example.foodiesapp.cart.Cart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegisterActivity : AppCompatActivity() {
    var username:EditText?=null
    var email:EditText?=null
    var pass:EditText?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_activity)
         username= findViewById<EditText>(R.id.et_name)
         email= findViewById<EditText>(R.id.et_email)
        pass= findViewById<EditText>(R.id.et_password)

        val btnRegister= findViewById<Button>(R.id.btn_save)
        btnRegister.setOnClickListener {
            makeRequest()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
 }


     fun makeRequest()
    {

        val api = Retrofit.Builder()
            .baseUrl("https://murmuring-temple-54993.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(userInterface::class.java)

        val requestModel = User(username?.text.toString(),  email?.text.toString(),pass?.text.toString())
        api.register(requestModel)?.enqueue(
            object : Callback<ResponseModel> {
                override fun onResponse(
                    call: Call<ResponseModel>,
                    response: Response<ResponseModel>
                ) {
                    Toast.makeText(this@RegisterActivity,"please login" ,Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                    Log.d("##","failure")
                    Toast.makeText(this@RegisterActivity,"can't register", Toast.LENGTH_LONG).show()
                }

            }
        )
    }
}