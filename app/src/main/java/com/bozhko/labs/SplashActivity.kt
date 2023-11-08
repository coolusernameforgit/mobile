package com.bozhko.labs

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        when (checkSharedPrefs()) {
            "autologin" -> {
                val intent = Intent(this, ContentActivity::class.java)
                startActivity(intent)
            }
            "login" -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
            "register" -> {
                val intent = Intent(this, RegistrationActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun checkSharedPrefs(): String {
        val sharedPrefs = getSharedPreferences("settings", Context.MODE_PRIVATE)
        if (sharedPrefs.contains("id") && sharedPrefs.contains("password")) {
            return if (sharedPrefs.getString("autologin", "false") == "true")
                "autologin"
            else
                "login"
        }
        return "register"
    }
}