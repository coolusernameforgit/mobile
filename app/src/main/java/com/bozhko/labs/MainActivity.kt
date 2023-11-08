package com.bozhko.labs

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, SplashActivity::class.java)
        startActivity(intent)
    }

    companion object {
        public fun writeToSharedPrefs(context: Context, map: Map<String, String>) {
            val sharedPrefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
            map.forEach {(key, value) ->
                sharedPrefs.edit().putString(key, value).apply()
            }
        }

        public fun resetSharedPrefs(context: Context) {
            val sharedPrefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
            sharedPrefs.edit().remove("id").apply()
            sharedPrefs.edit().remove("password").apply()
            sharedPrefs.edit().remove("autologin").apply()
        }
    }
}