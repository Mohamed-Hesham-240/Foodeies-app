package com.example.foodiesapp.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.foodiesapp.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_activity)

        val btnRegister= findViewById<Button>(R.id.btn_save)
        makeRequest()
        btnRegister.setOnClickListener{


} }


    private fun makeRequest()
    {
        val api = Retrofit.Builder()
            .baseUrl("https://murmuring-temple-54993.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(userInterface::class.java)

        val requestModel = User("username123", "hello123","123")

        api.register(requestModel)?.enqueue(
            object : Callback<ResponseModel> {
                override fun onResponse(
                    call: Call<ResponseModel>,
                    response: Response<ResponseModel>
                ) { Toast.makeText(this@RegisterActivity,"Register done !",Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                    Toast.makeText(this@RegisterActivity,t.toString(),Toast.LENGTH_LONG).show()
                }

            }
        )
    }
}