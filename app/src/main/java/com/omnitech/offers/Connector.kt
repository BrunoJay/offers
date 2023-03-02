package com.omnitech.offers

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.omnitech.offers.models.Offer
import java.io.IOException
import java.io.InputStream

class Connector {

    companion object {
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
    }
}
