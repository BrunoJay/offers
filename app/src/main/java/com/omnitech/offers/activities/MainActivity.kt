package com.omnitech.offers.activities

import android.graphics.Point
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.omnitech.offers.R
import com.omnitech.offers.fragments.DetailsFragment
import com.omnitech.offers.fragments.ViewPagerFragment

class MainActivity : AppCompatActivity() {

    private val fragment1: Fragment = ViewPagerFragment()
    private val fragment2: Fragment = DetailsFragment()
    private val fm = supportFragmentManager
    private val active = fragment1

    private var lp: LinearLayout.LayoutParams? = null
    private var frameLayout: FrameLayout? = null
    private var screenHeight = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        screenHeight = size.y

        frameLayout = findViewById(R.id.main_container)
        lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, screenHeight)
        frameLayout!!.layoutParams = lp

        fm.beginTransaction().add(R.id.main_container, fragment2, "2").hide(fragment2).commit()
        fm.beginTransaction().add(R.id.main_container, fragment1, "1").commit()
        fm.beginTransaction().hide(active).show(fragment1).commit()
    }
}
