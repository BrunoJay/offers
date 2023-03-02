package com.omnitech.offers.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.omnitech.offers.R
import com.omnitech.offers.models.Offer
import com.squareup.picasso.Picasso

class FavouritesAdapter(var context: Context, var arrayList: ArrayList<Offer>) :
    RecyclerView.Adapter<FavouritesAdapter.ItemHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val viewHolder = LayoutInflater.from(parent.context)
            .inflate(R.layout.grid_layout_list_item, parent, false)
        val errorHolder = LayoutInflater.from(parent.context)
            .inflate(R.layout.error_view, parent, false)
        return if(arrayList.size>0) ItemHolder(viewHolder) else ItemHolder(errorHolder)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {

        if (arrayList.size==0) {
            holder.errorMessage.text = "No Favourites"
        }

        val offer: Offer = arrayList.get(position)

        Picasso.get()
            .load(offer.url)
            .placeholder(R.drawable.image)
            .error(R.drawable.image_error)
            .into(holder.icons)

        holder.name.text = offer.name

        holder.currentValue.setOnClickListener {
            Toast.makeText(context, offer.description, Toast.LENGTH_LONG).show()
        }
    }

    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var icons = itemView.findViewById<ImageView>(R.id.icons_image)
        var currentValue = itemView.findViewById<TextView>(R.id.current_value)
        var name = itemView.findViewById<TextView>(R.id.name)
        var errorMessage = itemView.findViewById<TextView>(R.id.message)

    }
}
