package com.codecool.doggiegang

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class RegistrationUiTest {
    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(RegistrationActivity::class.java)

    @Test
    fun mainActivityPassTest() {
        onView(withId(R.id.email)).perform(scrollTo())
        onView(withId(R.id.email)).perform(ViewActions.typeText("mikey@mikey.com"))
        onView(withId(R.id.username)).perform(scrollTo())
        onView(withId(R.id.username)).perform(ViewActions.typeText("Mikey Mc'Daniels"))
        onView(withId(R.id.password)).perform(scrollTo())
        onView(withId(R.id.password)).perform(ViewActions.typeText("Mikey18!"))
        onView(withId(R.id.dogName)).perform(scrollTo())
        onView(withId(R.id.dogName)).perform(ViewActions.typeText("Lola"))
        onView(withId(R.id.introduction)).perform(scrollTo())
        onView(withId(R.id.introduction)).perform(ViewActions.typeText("Hey I'm Mike and this is Lola my dearest dog."))
        onView(withId(R.id.location)).perform(scrollTo())
        onView(withId(R.id.location)).perform(ViewActions.typeText("Budapest"))
        onView(withId(R.id.registerButton)).perform(scrollTo())
        onView(withId(R.id.registerButton)).perform(ViewActions.click())
        onView(withId(R.id.success)).perform(scrollTo())
        onView(withId(R.id.success)).check(matches(isDisplayed()))
    }

}