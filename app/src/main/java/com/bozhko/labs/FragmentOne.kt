package com.bozhko.labs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import androidx.recyclerview.widget.RecyclerView
import com.bozhko.labs.databinding.FragmentOneBinding

class FragmentOne : Fragment() {
    private val myAdapter = PhonesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_one, container, false)
        loadData()
        root.findViewById<RecyclerView>(R.id.recycler_view).layoutManager = LinearLayoutManager(requireContext())
        root.findViewById<RecyclerView>(R.id.recycler_view).adapter = myAdapter
        return root
/*
        val rvRecycler = root.findViewById<RecyclerView>(R.id.recycler_view)
        rvRecycler.rootView.findViewById<>()
        rvRecycler.layoutManager = LinearLayoutManager(requireContext())
        rvRecycler.adapter = myAdapter
        return rvRecycler.rootView
*/
/*
        root.
        val binding = FragmentOneBinding.inflate(layoutInflater)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = myAdapter
        return binding.root
*/
    }

    private fun loadData() {
        myAdapter.setupPhones(PhonesData.phones)
    }
}