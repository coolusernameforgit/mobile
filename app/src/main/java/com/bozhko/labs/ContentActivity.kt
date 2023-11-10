package com.bozhko.labs

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class ContentActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)
        navController = Navigation.findNavController(this, R.id.fragment_container)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNav.setupWithNavController(navController)
        //вызов сплеш фрагмента
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