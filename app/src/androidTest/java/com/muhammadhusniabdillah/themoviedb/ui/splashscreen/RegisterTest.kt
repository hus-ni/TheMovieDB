package com.muhammadhusniabdillah.themoviedb.ui.splashscreen


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.muhammadhusniabdillah.themoviedb.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class RegisterTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(TheMoviesDbInitialActivity::class.java)

    @Test
    fun registerTest() {
//        Thread.sleep(5000)
        val materialTextView = onView(
            allOf(
                withId(R.id.tv_register), withText("Don't have account? Register"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.cv_login),
                        0
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        materialTextView.perform(click())

        val textInputEditText = onView(
            allOf(
                withId(R.id.et_register_name),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.til_register_username),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText.perform(replaceText("husni"), closeSoftKeyboard())

        val textInputEditText2 = onView(
            allOf(
                withId(R.id.et_register_email),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.til_register_email),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText2.perform(replaceText("husni@binar.com"), closeSoftKeyboard())

        val textInputEditText3 = onView(
            allOf(
                withId(R.id.et_register_password),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.til_register_password),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText3.perform(replaceText("123"), closeSoftKeyboard())

        val textInputEditText4 = onView(
            allOf(
                withId(R.id.et_register_passwordConfirm),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.til_register_passwordConfirm),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText4.perform(replaceText("123"), closeSoftKeyboard())

        val textInputEditText5 = onView(
            allOf(
                withId(R.id.et_register_passwordConfirm), withText("123"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.til_register_passwordConfirm),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText5.perform(pressImeActionButton())

        val materialButton = onView(
            allOf(
                withId(R.id.btn_register), withText("Register"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.cv_register),
                        0
                    ),
                    5
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
