package com.bozhko.labs

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
<<<<<<< HEAD
import androidx.navigation.fragment.NavHostFragment
=======
import android.widget.Button
>>>>>>> newbranch

class FragmentTwo : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
<<<<<<< HEAD
        val root = inflater.inflate(R.layout.fragment_splash, container, false)
        val navController = NavHostFragment.findNavController(this)
        //navController.navigate()
        return root
=======
        val view = inflater.inflate(R.layout.fragment_two, container, false)

        val bReset = view.findViewById<Button>(R.id.b_reset)
        bReset.setOnClickListener {
            ContentActivity.Companion.resetSharedPrefs(
                requireActivity().getSharedPreferences("settings", Context.MODE_PRIVATE)
            )
        }

        return view
>>>>>>> newbranch
    }
}