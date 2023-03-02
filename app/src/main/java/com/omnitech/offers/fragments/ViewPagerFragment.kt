package com.omnitech.offers.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.omnitech.offers.R
import com.omnitech.offers.adapters.ViewPagerAdapter
import com.omnitech.offers.databinding.ViewPagerFragmentBinding

class ViewPagerFragment : Fragment() {

    private lateinit var binding: ViewPagerFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView = inflater.inflate(R.layout.view_pager_fragment, container, false)
        binding = ViewPagerFragmentBinding.inflate(layoutInflater)

        val viewPagerAdapter = ViewPagerAdapter(rootView.context, childFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = viewPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)

        return binding.root
    }
}
