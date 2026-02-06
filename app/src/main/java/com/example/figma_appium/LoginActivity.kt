package com.example.figma_appium

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.figma_appium.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            // Navigate to success screen
            startActivity(Intent(this, SuccessActivity::class.java))
            finish()
        }

        binding.btnFacebook.setOnClickListener {
            // Handle Facebook login
        }

        binding.btnGoogle.setOnClickListener {
            // Handle Google login
        }

        binding.btnApple.setOnClickListener {
            // Handle Apple login
        }
    }
}
