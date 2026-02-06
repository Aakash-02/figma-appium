package com.example.figma_appium

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.figma_appium.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignUp.setOnClickListener {
            if (validateInputs()) {
                // Navigate to success screen
                startActivity(Intent(this, SuccessActivity::class.java))
                finish()
            }
        }
    }

    private fun validateInputs(): Boolean {
        val userName = binding.etUserName.text.toString()
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        val confirmPassword = binding.etConfirmPassword.text.toString()
        val termsAccepted = binding.cbTerms.isChecked

        if (userName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password != confirmPassword) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            return false
        }

        if (!termsAccepted) {
            Toast.makeText(this, "Please accept terms and conditions", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
}
