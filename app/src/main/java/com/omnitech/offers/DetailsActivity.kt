package com.omnitech.offers

import android.media.Image
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.omnitech.offers.models.Offer
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class DetailsActivity : AppCompatActivity() {
    var favView:ImageButton? = null
    var nonFavView:ImageButton? = null
    var offer:Offer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.details_activity)

        val id = intent.getStringExtra("offer_id")
        offer = Connector.getOfferById(this, id)

        findViewById<TextView>(R.id.name).text = offer?.name
        findViewById<TextView>(R.id.current_value).text = offer?.current_value
        findViewById<TextView>(R.id.description).text = offer?.description
        findViewById<TextView>(R.id.terms).text = offer?.terms

        Picasso.get()
            .load(offer?.url)
            .placeholder(R.drawable.image)
            .error(R.drawable.image_error)
            .into(findViewById<ImageView>(R.id.offer_image))

        favView = findViewById<ImageButton>(R.id.favourite)
        nonFavView = findViewById<ImageButton>(R.id.not_favourite)

        favView!!.setOnClickListener {
            Connector.setFavouriteState(this,offer?.id, "favourite")
            setFavourites()
        }

        nonFavView!!.setOnClickListener {
            Connector.setFavouriteState(this,offer?.id, "not_favourite")
            setFavourites()
        }
    }

    private fun setFavourites() {
        val favState = Connector.getOfferFavouriteState(this,offer?.id)
        if(favState=="favourite") {
            favView!!.visibility = View.VISIBLE
            nonFavView!!.visibility = View.INVISIBLE
        } else  {
            favView!!.visibility = View.INVISIBLE
            nonFavView!!.visibility = View.VISIBLE
        }
    }
}
