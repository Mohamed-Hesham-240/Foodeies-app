package com.example.foodiesapp.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.example.foodiesapp.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_activity)

        val btnRegister= findViewById<Button>(R.id.btn_save)

        btnRegister.setOnClickListener{
            makeRequest()
} }


    private fun makeRequest()
    {
        val api = Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(userInterface::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = api.getallUsers()

                for (user in response.users) {
                    Log.d("Register", "Result + ${user}")
                   // addToList()
                }
                withContext(Dispatchers.Main) {
                  //  setUpRecyclerView()
                }
            } catch (e: Exception) {
                Log.d("Register", e.toString())

            }
        }
    }
}