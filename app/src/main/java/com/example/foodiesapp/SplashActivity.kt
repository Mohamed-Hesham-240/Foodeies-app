package com.example.foodiesapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity


class SplashActivity : AppCompatActivity() {
    private val SPLASH_SCREEN_DELAY : Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideActionBar()
        setContentView(R.layout.activity_splash)
        setSplashHandler()
    }

    private fun setSplashHandler(){
        Handler().postDelayed(Runnable {
            goToLogin()
            finish()
        }, SPLASH_SCREEN_DELAY)
    }

    private fun hideActionBar(){
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }
    }

    private fun goToLogin(){
        val loginIntent = Intent(this,MainActivity::class.java )
        startActivity(loginIntent)
    }


}