package com.omnitech.offers.activities

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.omnitech.offers.R
import com.omnitech.offers.models.Offer
import com.omnitech.offers.utils.DataHandler
import com.squareup.picasso.Picasso

class DetailsActivity : AppCompatActivity() {
    var offer: Offer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.details_activity)

        val id = intent.getStringExtra("offer_id")
        offer = DataHandler.getOfferById(this, id)

        findViewById<TextView>(R.id.name).text = offer?.name
        findViewById<TextView>(R.id.current_value).text = offer?.current_value
        findViewById<TextView>(R.id.description).text = offer?.description
        findViewById<TextView>(R.id.terms).text = offer?.terms

        Picasso.get()
            .load(offer?.url)
            .placeholder(R.drawable.image)
            .error(R.drawable.image_error)
            .into(findViewById<ImageView>(R.id.offer_image))

        val favState = DataHandler.getOfferFavouriteState(this, offer?.id)
        setFavourites(favState!!)

        findViewById<ImageButton>(R.id.favourite)?.setOnClickListener {
            val newState = DataHandler.setFavouriteState(this, offer?.id, "not_favourite")
            setFavourites(newState!!)
        }

        findViewById<ImageButton>(R.id.not_favourite).setOnClickListener {
            val newState = DataHandler.setFavouriteState(this, offer?.id, "favourite")
            setFavourites(newState!!)
        }
    }

    private fun setFavourites(favState: String) {
        if (favState == "favourite") {
            findViewById<ImageButton>(R.id.favourite).isVisible = true
            findViewById<ImageButton>(R.id.not_favourite).isVisible = false
        } else {
            findViewById<ImageButton>(R.id.favourite).isVisible = false
            findViewById<ImageButton>(R.id.not_favourite).isVisible = true
        }
    }
}
