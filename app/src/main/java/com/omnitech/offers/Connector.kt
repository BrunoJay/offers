package com.omnitech.offers

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.omnitech.offers.models.Offer
import java.io.IOException
import java.io.InputStream

class Connector {
    companion object {
        var sp: SharedPreferences? = null
        var spEditor: SharedPreferences.Editor? = null

        fun getOffersDataList(context: Context): ArrayList<Offer>? {
            // Create a Gson instance
            val gson = Gson()

            // Define a type for your list of Offer objects
            val type = object : TypeToken<List<Offer>>() {}.type

            return try {
                val inputStream: InputStream = context.assets.open("offers.json")
                val size: Int = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                val jsonString = String(buffer)
                // Parse your JSON string using fromJson() method
                gson.fromJson(jsonString, type)
            } catch (e: IOException) {
                e.printStackTrace()
                null
            }
        }

        fun getOfferById(context: Context, offerId: String?): Offer? {
            var result: Offer? = null
            val offers: List<Offer> = getOffersDataList(context)!!
            for (offer in offers) {
                if (offer.id == offerId) result = offer
            }
            return result
        }

        fun getFavourites(context: Context): ArrayList<Offer> {
            sp = context.getSharedPreferences("key", 0)
            spEditor = sp?.edit()

            val offers = getOffersDataList(context)
            val results = ArrayList<Offer>()
            offers?.forEach {
                if (sp?.getString(it.id, null) == "favourite") {
                    results.add(it)
                }
            }
            return results
        }

        fun setFavouriteState(context: Context, offerId: String?, state:String):String? {
            sp = context.getSharedPreferences("key", 0)
            spEditor = sp?.edit()

            spEditor?.putString(offerId, state)
            spEditor?.apply()
            return getOfferFavouriteState(context, offerId)
        }

        fun getOfferFavouriteState(context: Context, offerId: String?): String? {
            sp = context.getSharedPreferences("key", 0)
            return sp?.getString(offerId,null)
        }
    }
}
