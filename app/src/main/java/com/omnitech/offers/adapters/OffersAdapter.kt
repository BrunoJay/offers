package com.omnitech.offers.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.omnitech.offers.R
import com.omnitech.offers.models.Offer
import com.squareup.picasso.Picasso


class OffersAdapter(var context: Context, var arrayList: ArrayList<Offer>) :
    RecyclerView.Adapter<OffersAdapter.ItemHolder>() {
    var parentId: Int? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val viewHolder = LayoutInflater.from(parent.context)
            .inflate(R.layout.grid_layout_list_item, parent, false)
        parentId = parent.id
        return ItemHolder(viewHolder)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {

        val offer: Offer = arrayList.get(position)

        Picasso.get()
            .load(offer.url)
            .placeholder(R.drawable.image)
            .error(R.drawable.image_error)
            .into(holder.icons)

        holder.name.text = offer.name
        holder.currentValue.text = offer.current_value
        if (checkIfOfferIsAmongFavourites(context, offer.id)) {
            holder.isFavourite.visibility = View.VISIBLE
        } else {
            holder.isFavourite.visibility = View.INVISIBLE
        }

        holder.currentValue.setOnClickListener {
            Toast.makeText(context, offer.description, Toast.LENGTH_LONG).show()
        }
    }

    fun checkIfOfferIsAmongFavourites(context: Context, offerId: String): Boolean {
        val sp = context.getSharedPreferences("key", 0)
        val value = sp?.getString(offerId, null)
        return value == "favourite"
    }

    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var icons = itemView.findViewById<ImageView>(R.id.icons_image)
        var currentValue = itemView.findViewById<TextView>(R.id.current_value)
        var name = itemView.findViewById<TextView>(R.id.name)
        var isFavourite = itemView.findViewById<ImageButton>(R.id.is_favourite)

    }
}
