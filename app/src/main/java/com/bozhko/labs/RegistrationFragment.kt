package com.bozhko.labs

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.NavHostFragment
import android.graphics.Color
import android.widget.Toast
import androidx.fragment.app.FragmentManager

class RegistrationFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_registration, container, false)

        val bPhone = view.findViewById<Button>(R.id.b_phone)
        val bEmail = view.findViewById<Button>(R.id.b_email)
        val etId = view.findViewById<EditText>(R.id.et_id)

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

        val bReg = view.findViewById<Button>(R.id.b_reg)
        val etPass = view.findViewById<EditText>(R.id.et_pass)
        val etPassRep = view.findViewById<EditText>(R.id.et_pass_rep)

        bReg.setOnClickListener {
            if (validateInput(etId, etPass, etPassRep)) {
                ContentActivity.Companion.writeToSharedPrefs(
                    requireActivity().getSharedPreferences("settings", Context.MODE_PRIVATE),
                    mapOf(
                        "id" to etId.text.toString(),
                        "password" to etPass.text.toString()
                    )
                )

                val navController = NavHostFragment.findNavController(this)
                navController.navigate(R.id.FragmentOne)
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

    private fun validateInput(etId: EditText, etPass: EditText, etPassRep: EditText): Boolean {
        return validateId(etId) && validatePass(etPass, etPassRep)
    }

    private fun validateId(etId: EditText): Boolean {
        if (etId.hint == "Почта" && !etId.text.contains("@")) {
            Toast.makeText(activity, "Неправильно введена почта: отсутствует @",
                Toast.LENGTH_LONG).show()
            return false
        }

        if (etId.hint == "Номер телефона" && !etId.text.contains("+")) {
            Toast.makeText(activity, "Неправильно введен номер: отсутствует +",
                Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

    private fun validatePass(etPass: EditText, etPassRep: EditText): Boolean {
        if (etPass.length() < 8) {
            Toast.makeText(activity, "Неправильно введен пароль: символов меньше 8",
                Toast.LENGTH_LONG).show()
            return false
        }

        if (etPass.text.toString() != etPassRep.text.toString()) {
            Toast.makeText(activity, "Введенные пароли не совпадают",
                Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }
}