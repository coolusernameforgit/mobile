package com.bozhko.labs

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_splash, container, false)
        val navController = NavHostFragment.findNavController(this)

        when (checkSharedPrefs()) {
            "login" -> {
                navController.navigate(R.id.LoginFragment)
            }
            "register" -> {
                navController.navigate(R.id.RegistrationFragment)
            }
        }

        return root
    }

    private fun checkSharedPrefs(): String {
        val sharedPrefs = requireActivity().getSharedPreferences("settings", Context.MODE_PRIVATE)
        if (sharedPrefs.contains("id") && sharedPrefs.contains("password")) {
            return "login"
        }
        return "register"
    }
}