package com.harshita.retrofitlogin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.harshita.retrofitlogin.databinding.ActivityLogoutBinding

class LogoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnLogout.setOnClickListener {
            finish()
        }
    }
}