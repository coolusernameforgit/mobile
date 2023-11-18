package com.bozhko.labs

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.fragment.NavHostFragment

class ContentActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)
        navController = Navigation.findNavController(this, R.id.fragment_container)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNav.setupWithNavController(navController)

        navController.navigate(R.id.SplashFragment)

    }

    companion object {
        public fun writeToSharedPrefs(sharedPrefs: SharedPreferences, map: Map<String, String>) {
            map.forEach {(key, value) ->
                sharedPrefs.edit().putString(key, value).apply()
            }
        }

        public fun resetSharedPrefs(sharedPrefs: SharedPreferences) {
            sharedPrefs.edit().remove("id").apply()
            sharedPrefs.edit().remove("password").apply()
            sharedPrefs.edit().remove("autologin").apply()
        }
    }
}