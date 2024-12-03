package com.example.internship

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class SplashScreen : AppCompatActivity() {

    lateinit var logo : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        logo = findViewById(R.id.imageView)

//        rotateAnimation()

        val iHome = Intent(this@SplashScreen, MainActivity::class.java)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(iHome)
            finish()
        }, 2000)
    }

}