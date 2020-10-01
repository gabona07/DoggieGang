package com.codecool.doggiegang

import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
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
        onView(withId(R.id.successText)).perform(scrollTo())
        onView(withId(R.id.successText)).check(matches(isDisplayed()))
    }

    @Test
    fun mainActivityFailTest() {
        onView(withId(R.id.email)).perform(scrollTo())
        onView(withId(R.id.email)).perform(ViewActions.typeText("Mikey Mc'Daniels"))
        onView(withId(R.id.password)).perform(scrollTo())
        onView(withId(R.id.password)).perform(ViewActions.typeText("abc"))
        closeSoftKeyboard()
        onView(withId(R.id.registerButton)).perform(scrollTo())
        onView(withId(R.id.registerButton)).perform(click())
        onView(withId(R.id.usernameField)).check(matches(hasDescendant(withText("Field can't be empty!"))))
        onView(withId(R.id.emailField)).check(matches(hasDescendant(withText("Invalid email format!"))))
        onView(withId(R.id.passwordField)).check(matches(hasDescendant(withText("Password is too weak!"))))
        onView(withId(R.id.dogNameField)).check(matches(hasDescendant(withText("Field can't be empty!"))))
        onView(withId(R.id.introductionField)).check(matches(hasDescendant(withText("Field can't be empty!"))))
        onView(withId(R.id.locationField)).check(matches(hasDescendant(withText("Field can't be empty!"))))

    }
}