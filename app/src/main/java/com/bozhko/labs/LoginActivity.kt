package com.bozhko.labs

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_login)

        val bLogin = findViewById<Button>(R.id.button_Login)
        bLogin.setOnClickListener {
            val result = validateInput()
            if (result) {
                val chbAutologin = findViewById<CheckBox>(R.id.chb_autologin)
                ContentActivity.Companion.writeToSharedPrefs(this,
                    mapOf("autologin" to chbAutologin.isChecked.toString()))
                val intent = Intent(this, ContentActivity::class.java)
                startActivity(intent)
            } else {
                val toast = Toast.makeText(this, "Неправильные логин или пароль",
                    Toast.LENGTH_LONG)
                toast.show()
            }
        }

        val bReset = findViewById<Button>(R.id.button_reset)
        bReset.setOnClickListener {
            ContentActivity.Companion.resetSharedPrefs(this)
        }
    }

    private fun validateInput(): Boolean {
        val sharedPrefs = getSharedPreferences("settings", Context.MODE_PRIVATE)
        val idPrefs = sharedPrefs.getString("id", "")
        val passPrefs = sharedPrefs.getString("password", "")
        val etId = findViewById<EditText>(R.id.et_id_log)
        val etPass = findViewById<EditText>(R.id.et_pass_log)

        return (idPrefs != "" && idPrefs == etId.text.toString() &&
                passPrefs != "" && passPrefs == etPass.text.toString())
    }
}