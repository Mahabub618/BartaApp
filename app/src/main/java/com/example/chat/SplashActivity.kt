package com.example.chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()

        val TimeOut = 2000
        val homeIntent = Intent(this@SplashActivity, MainActivity::class.java)
        Handler().postDelayed({
            startActivity(homeIntent)
            finish()
        }, TimeOut.toLong())
    }
}