package com.omnitech.offers

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.omnitech.offers.models.Offer
import com.squareup.picasso.Picasso

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.details_activity)

        val id = intent.getStringExtra("offer_id")
        val offer: Offer? = Connector.getOfferById(this, id)

        findViewById<TextView>(R.id.name).text = offer?.name
        findViewById<TextView>(R.id.current_value).text = offer?.current_value
        findViewById<TextView>(R.id.description).text = offer?.description
        findViewById<TextView>(R.id.terms).text = offer?.terms

        Picasso.get()
            .load(offer?.url)
            .placeholder(R.drawable.image)
            .error(R.drawable.image_error)
            .into(findViewById<ImageView>(R.id.offer_image))
    }
}
