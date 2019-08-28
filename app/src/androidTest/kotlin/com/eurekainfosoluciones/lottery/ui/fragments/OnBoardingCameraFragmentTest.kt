package com.eurekainfosoluciones.lottery.ui.fragments

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.eurekainfosoluciones.lottery.R
import com.eurekainfosoluciones.lottery.ui.activities.MainActivity
import org.junit.Rule
import org.junit.Test

class OnBoardingCameraFragmentTest {

    @JvmField
    @Rule
    val activityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun seesCameraCta() {
        onView(withId(R.id.cameraCta)).check(matches(ViewMatchers.withText(R.string.camera_cta)))
    }
}