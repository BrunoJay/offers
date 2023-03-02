package com.omnitech.offers.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.omnitech.offers.utils.DataHandler
import com.omnitech.offers.R
import com.omnitech.offers.adapters.FavouritesAdapter
import com.omnitech.offers.models.Offer

class FavouriteFragment : Fragment() {

    private var recyclerView: RecyclerView? = null
    private var offersData: ArrayList<Offer>? = null
    private var gridLayoutManager: GridLayoutManager? = null
    private var favouritesAdapter: FavouritesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val rootView = inflater.inflate(R.layout.favourites_fragment, container, false)

        recyclerView = rootView.findViewById(R.id.favourites_recycler_view)
        gridLayoutManager = GridLayoutManager(rootView.context, 2, LinearLayoutManager.VERTICAL, false)
        recyclerView?.layoutManager = gridLayoutManager
        recyclerView?.setHasFixedSize(true)

        offersData = ArrayList()
        offersData = DataHandler.getFavourites(rootView.context)
        favouritesAdapter = FavouritesAdapter(rootView.context, offersData!!)
        recyclerView?.adapter =  favouritesAdapter

        return rootView
    }
}
