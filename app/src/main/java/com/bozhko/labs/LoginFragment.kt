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
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        val sharedPrefs = requireActivity().getSharedPreferences("settings", Context.MODE_PRIVATE)
        val idPrefs = sharedPrefs.getString("id", "").toString()
        val passPrefs = sharedPrefs.getString("password", "").toString()

        val etId = view?.findViewById<EditText>(R.id.et_id_log)
        val etPass = view?.findViewById<EditText>(R.id.et_pass_log)

        etId?.setText(idPrefs)
        etPass?.setText(passPrefs)

        val bLogin = view.findViewById<Button>(R.id.b_login)

        bLogin.setOnClickListener {
            val id = etId?.text.toString()
            val pass = etPass?.text.toString()

            tryLogin(id, pass)
        }

        val autoLoginPrefs = sharedPrefs.getString("autologin", "false").toString()
        if (autoLoginPrefs == "true") {
            bLogin.callOnClick()
        }

        val bReset = view.findViewById<Button>(R.id.b_reset)
        bReset.setOnClickListener {
            ContentActivity.Companion.resetSharedPrefs(
                requireActivity().getSharedPreferences("settings", Context.MODE_PRIVATE)
            )
        }

        return view
    }

    private fun tryLogin(id: String, pass: String) {
        val auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(id, pass)
            .addOnSuccessListener {
                writeToSharedPrefs(id, pass)
                NavHostFragment.findNavController(this).navigate(R.id.FragmentOne)
            }
            .addOnFailureListener {
                Toast.makeText(activity, "Неправильные логин или пароль", Toast.LENGTH_LONG)
                    .show()
            }
    }

    private fun writeToSharedPrefs(id: String, pass: String) {
        val chbAutologin = view?.findViewById<CheckBox>(R.id.chb_autologin)

        ContentActivity.Companion.writeToSharedPrefs(
            requireActivity().getSharedPreferences("settings", Context.MODE_PRIVATE),
            mapOf("id" to id,  "password" to pass,
                "autologin" to chbAutologin?.isChecked.toString())
        )
    }
}