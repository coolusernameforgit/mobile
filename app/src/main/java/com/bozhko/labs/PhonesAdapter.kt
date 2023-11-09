package com.bozhko.labs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PhonesAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var phonesList = ArrayList<PhoneModel>()
    override fun getItemCount(): Int {
        return phonesList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PhonesViewHolder) {
            holder.bind(phone = phonesList[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.recyclerview_item, parent, false)
        return PhonesViewHolder(itemView)
    }

    fun setupPhones(phonesArr: Array<PhoneModel>) {
        phonesList.clear()
        phonesList.addAll(phonesArr)
        notifyDataSetChanged()
    }

    class PhonesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(phone: PhoneModel) {
            itemView.findViewById<TextView>(R.id.tv_phone_name).text = phone.name
            itemView.findViewById<TextView>(R.id.tv_date).text = phone.date
            itemView.findViewById<TextView>(R.id.tv_price).text = phone.price
            itemView.findViewById<TextView>(R.id.tv_score).text = phone.score
        }
    }
}

