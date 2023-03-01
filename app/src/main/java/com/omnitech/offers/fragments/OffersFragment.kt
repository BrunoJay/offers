package com.omnitech.offers.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.omnitech.offers.R
import com.omnitech.offers.adapters.OffersAdapter
import com.omnitech.offers.models.Offer
import java.io.IOException
import java.io.InputStream

class OffersFragment : Fragment() {

    private var recyclerView: RecyclerView? = null
    private var offersData: ArrayList<Offer>? = null
    private var gridLayoutManager: GridLayoutManager? = null
    private var offersAdapter: OffersAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val rootView: View = inflater.inflate(R.layout.offers_fragment, container, false)

        recyclerView = rootView.findViewById(R.id.offers_recycler_view)
        gridLayoutManager =
            GridLayoutManager(rootView.context, 3, LinearLayoutManager.VERTICAL, false)
        recyclerView?.layoutManager = gridLayoutManager
        recyclerView?.setHasFixedSize(true)

        offersData = ArrayList()
        offersData = getOffersDataList(rootView.context)
        offersAdapter = OffersAdapter(rootView.context, offersData!!)
        recyclerView?.adapter = offersAdapter

        return rootView
    }

    private fun getOffersDataList(context: Context): ArrayList<Offer>? {
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
