package com.codecool.doggiegang

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.runner.AndroidJUnit4
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
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
        onView(withId(R.id.username)).perform(scrollTo())
        onView(withId(R.id.username)).perform(ViewActions.typeText("mikey@mikey.com"))
        onView(withId(R.id.password)).perform(scrollTo())
        onView(withId(R.id.password)).perform(ViewActions.typeText("abc"))
        onView(withId(R.id.registerButton)).perform(scrollTo())
        onView(withId(R.id.registerButton)).perform(ViewActions.click())
        onView(withId(R.id.username)).check(matches(hasTextInputLayoutErrorText("Invalid name format!")))
        onView(withId(R.id.emailField)).check(matches(hasTextInputLayoutErrorText("Wrong e-mail format!")))
        onView(withId(R.id.passwordField)).check(matches(hasTextInputLayoutErrorText("Password too weak, it must contain one lowercase, one uppercase, one digit and one special character.")))
    }

    private fun hasTextInputLayoutErrorText(expectedErrorText: String): Matcher<View>? {
        return object : TypeSafeMatcher<View>() {
            override fun matchesSafely(view: View): Boolean {
                if (view !is TextInputLayout) {
                    return false
                }
                val error = (view).error ?: return false
                val hint = error.toString()
                return expectedErrorText == hint
            }

            override fun describeTo(description: Description) {}
        }
    }

}