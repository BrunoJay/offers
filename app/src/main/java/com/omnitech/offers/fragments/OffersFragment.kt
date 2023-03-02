package com.omnitech.offers.fragments

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
import com.omnitech.offers.adapters.OffersAdapter
import com.omnitech.offers.models.Offer

class OffersFragment : Fragment() {

    private var recyclerView: RecyclerView? = null
    private var offersData: ArrayList<Offer>? = null
    private var gridLayoutManager: GridLayoutManager? = null
    private var offersAdapter: OffersAdapter? = null

    private var sp: SharedPreferences? = null
    private var spEditor: SharedPreferences.Editor? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val rootView: View = inflater.inflate(R.layout.offers_fragment, container, false)

        sp = rootView.context.getSharedPreferences("key", 0)
        spEditor = sp?.edit()

        recyclerView = rootView.findViewById(R.id.offers_recycler_view)
        gridLayoutManager =
            GridLayoutManager(rootView.context, 2, LinearLayoutManager.VERTICAL, false)
        recyclerView?.layoutManager = gridLayoutManager
        recyclerView?.setHasFixedSize(true)

        offersData = ArrayList()
        offersData = Connector.getOffersDataList(rootView.context)
        offersAdapter = OffersAdapter(rootView.context, offersData!!)
        recyclerView?.adapter = offersAdapter

        //save favourite state to shared preferences
        for (offer in offersData!!) {
            val value = sp?.getString(offer.id, null)
            if(value.isNullOrEmpty()) {
                spEditor?.putString(offer.id, "not-favourite")
                spEditor?.apply()
            }
        }

        return rootView
    }
}
