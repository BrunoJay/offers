package com.omnitech.offers

import android.graphics.Point
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.fragment.app.Fragment
import com.omnitech.offers.fragments.DetailsFragment
import com.omnitech.offers.fragments.ViewPagerFragment
import com.omnitech.offers.ui.theme.NavigationTheme

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

        setContent {
            NavigationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Nagivation()
                }
            }
        }

/*        setContentView(R.layout.activity_main)

        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        screenHeight = size.y

        frameLayout = findViewById(R.id.main_container)
        lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, screenHeight)
        frameLayout!!.layoutParams = lp

        fm.beginTransaction().add(R.id.main_container, fragment2, "2").hide(fragment2).commit()
        fm.beginTransaction().add(R.id.main_container, fragment1, "1").commit()
        fm.beginTransaction().hide(active).show(fragment1).commit()*/
    }
}
