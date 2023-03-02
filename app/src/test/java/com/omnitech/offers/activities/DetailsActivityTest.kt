package com.omnitech.offers.activities

import android.content.Intent
import android.os.Bundle
import androidx.test.core.app.ActivityScenario
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


internal class DetailsActivityTest {

    private val OFFER_ID = "123"
    private var activity: DetailsActivity? = null

    @BeforeEach
    fun setUp() {
        val intent = Intent()
        val bundle = Bundle()
        bundle.putString("offer_id", OFFER_ID)
        intent.putExtras(bundle)
        val scenario: ActivityScenario<DetailsActivity> = ActivityScenario.launch(intent)
        scenario.onActivity { activity: DetailsActivity? ->
            this.activity = activity
        }
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun getOffer() {
    }

    @Test
    fun setOffer() {
    }

    @Test
    fun onCreate() {
    }
}
