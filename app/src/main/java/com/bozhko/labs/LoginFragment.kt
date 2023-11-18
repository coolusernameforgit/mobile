package com.bozhko.labs

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        val bLogin = view.findViewById<Button>(R.id.b_login)
        bLogin.setOnClickListener {
            if (validateInput()) {
                val chbAutologin = view.findViewById<CheckBox>(R.id.chb_autologin)
                ContentActivity.Companion.writeToSharedPrefs(
                    requireActivity().getSharedPreferences("settings", Context.MODE_PRIVATE),
                    mapOf("autologin" to chbAutologin.isChecked.toString()))

                val navController = NavHostFragment.findNavController(this)
                navController.navigate(R.id.FragmentOne)
            } else {
                Toast.makeText(activity, "Неправильные логин или пароль",
                    Toast.LENGTH_LONG)
                .show()
            }
        }

        val bReset = view.findViewById<Button>(R.id.b_reset)
        bReset.setOnClickListener {
            ContentActivity.Companion.resetSharedPrefs(
                requireActivity().getSharedPreferences("settings", Context.MODE_PRIVATE)
            )
        }

        return view
    }

    private fun validateInput(): Boolean {
        val sharedPrefs = requireActivity().getSharedPreferences("settings", Context.MODE_PRIVATE)
        val idPrefs = sharedPrefs.getString("id", "")
        val passPrefs = sharedPrefs.getString("password", "")
        val etId = view?.findViewById<EditText>(R.id.et_id_log)
        val etPass = view?.findViewById<EditText>(R.id.et_pass_log)

        return (idPrefs != "" && idPrefs == etId?.text.toString() &&
                passPrefs != "" && passPrefs == etPass?.text.toString())
    }
}