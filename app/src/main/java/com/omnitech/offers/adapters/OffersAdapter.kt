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

class OffersAdapter(var context: Context, var arrayList: ArrayList<Offer>) :
    RecyclerView.Adapter<OffersAdapter.ItemHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val viewHolder = LayoutInflater.from(parent.context)
            .inflate(R.layout.grid_layout_list_item, parent, false)
        return ItemHolder(viewHolder)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {

        val offer: Offer = arrayList.get(position)

        // Get an instance of Picasso
        val picasso: Picasso = Picasso.get()

        // Load an image from a URL into an ImageView
        val imageView:ImageView = holder.icons
        picasso.load(offer.url).into(imageView)
        holder.titles.text = offer.name

        holder.titles.setOnClickListener {
            Toast.makeText(context, offer.description, Toast.LENGTH_LONG).show()
        }

    }

    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var icons = itemView.findViewById<ImageView>(R.id.icons_image)
        var titles = itemView.findViewById<TextView>(R.id.text_label)

    }
}
