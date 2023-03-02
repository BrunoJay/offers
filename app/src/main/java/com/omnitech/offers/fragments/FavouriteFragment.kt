package com.omnitech.offers.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.omnitech.offers.Connector
import com.omnitech.offers.R
import com.omnitech.offers.adapters.FavouritesAdapter
import com.omnitech.offers.models.Offer

class FavouriteFragment : Fragment() {

    private var recyclerView: RecyclerView? = null
    private var offersData: ArrayList<Offer>? = null
    private var gridLayoutManager: GridLayoutManager? = null
    private var favouritesAdapter: FavouritesAdapter? = null

    private var sp: SharedPreferences? = null
    private var spEditor: SharedPreferences.Editor? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val rootView = inflater.inflate(R.layout.favourites_fragment, container, false)

        sp = rootView.context.getSharedPreferences("key", 0)
        spEditor = sp?.edit()

        recyclerView = rootView.findViewById(R.id.favourites_recycler_view)
        gridLayoutManager = GridLayoutManager(rootView.context, 2, LinearLayoutManager.VERTICAL, false)
        recyclerView?.layoutManager = gridLayoutManager
        recyclerView?.setHasFixedSize(true)

        offersData = ArrayList()
        offersData = getFavourites(rootView.context)
        favouritesAdapter = FavouritesAdapter(rootView.context, offersData!!)
        recyclerView?.adapter =  favouritesAdapter

        return rootView
    }

    private fun getFavourites(context: Context): ArrayList<Offer> {
        val offers = Connector.getOffersDataList(context)
        val results = ArrayList<Offer>()
        offers?.forEach {
            if (sp?.getString(it.id, null) == "favourite") {
                results.add(it)
            }
        }
        return results
    }
}
