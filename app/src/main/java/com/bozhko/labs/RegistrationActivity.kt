package com.bozhko.labs

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.bozhko.labs.MainActivity

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val bPhone = findViewById<Button>(R.id.b_phone)
        val bEmail = findViewById<Button>(R.id.b_email)
        val etId = findViewById<EditText>(R.id.et_id)

        bPhone.setOnClickListener {
            bPhone.setTextColor(Color.parseColor("#d5cefa"))
            bEmail.setTextColor(Color.parseColor("#bfbfbf"))
            etId.hint = "Номер телефона"
            etId.inputType = InputType.TYPE_CLASS_PHONE
        }

        bEmail.setOnClickListener {
            bEmail.setTextColor(Color.parseColor("#d5cefa"))
            bPhone.setTextColor(Color.parseColor("#bfbfbf"))
            etId.hint = "Почта"
            etId.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        }

        bPhone.callOnClick()

        val bReg = findViewById<Button>(R.id.b_reg)
        val etPass = findViewById<EditText>(R.id.et_pass)
        val etPassRep = findViewById<EditText>(R.id.et_pass_rep)

        bReg.setOnClickListener {
            val result = validateInput(etId, etPass, etPassRep)
            if (result) {
                MainActivity.Companion.writeToSharedPrefs(this, mapOf(
                    "id" to etId.text.toString(),
                    "password" to etPass.text.toString()))
                val intent = Intent(this, ContentActivity::class.java)
                startActivity(intent)
            }
        }

        val bReset = findViewById<Button>(R.id.b_reset)
        bReset.setOnClickListener {
            MainActivity.Companion.resetSharedPrefs(this)
        }
    }

    private fun validateInput(etId: EditText, etPass: EditText, etPassRep: EditText): Boolean {
        return validateId(etId) && validatePass(etPass, etPassRep)
    }

    private fun validateId(etId: EditText): Boolean {
        if (etId.hint == "Почта" && !etId.text.contains("@")) {
            val toast = Toast.makeText(this, "Неправильно введена почта: отсутствует @",
                Toast.LENGTH_LONG)
            toast.show()
            return false
        }

        if (etId.hint == "Номер телефона" && !etId.text.contains("+")) {
            val toast = Toast.makeText(this, "Неправильно введен номер: отсутствует +",
                Toast.LENGTH_LONG)
            toast.show()
            return false
        }
        return true
    }

    private fun validatePass(etPass: EditText, etPassRep: EditText): Boolean {
        if (etPass.length() < 8) {
            val toast = Toast.makeText(this, "Неправильно введен пароль: символов меньше 8",
                Toast.LENGTH_LONG)
            toast.show()
            return false
        }

        if (etPass.text.toString() != etPassRep.text.toString()) {
            val toast = Toast.makeText(this, "Введенные пароли не совпадают",
                Toast.LENGTH_LONG)
            toast.show()
            return false
        }
        return true
    }
}