package com.example.figma_appium.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.figma_appium.databinding.ActivitySplashBinding
import com.example.figma_appium.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivitySplashBinding
    private val splashTimeOut: Long = 3000 // 3 seconds
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // Navigate to MainActivity after splash timeout
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, splashTimeOut)
    }
}
