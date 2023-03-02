package com.omnitech.offers.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.omnitech.offers.R

class ErrorAdapter(var context: Context, var message: String) :
    RecyclerView.Adapter<ErrorAdapter.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ErrorAdapter.ItemHolder {
        val viewHolder = LayoutInflater.from(parent.context)
            .inflate(R.layout.error_view, parent, false)
        return ItemHolder(viewHolder)
    }

    override fun getItemCount(): Int {
        return 0
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.message.text = message
    }

    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var message: TextView = itemView.findViewById(R.id.message)
    }
}
