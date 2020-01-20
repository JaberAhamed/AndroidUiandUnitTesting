package com.example.chat;


import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class SecondActivityCheck {
    private String stringToBetyped;
    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);


    @Before
    public void initValidString() {
        // Specify a valid string.
        stringToBetyped = "Espresso";
    }



    @Test
    public void button_click_for_second_activity()
    {
        onView(withId(R.id.chatETID)).perform(typeText(stringToBetyped), closeSoftKeyboard());
        onView((withId(R.id.btn_next))).perform(click());
        onView((withId(R.id.text_second))).check(matches(isDisplayed()));

    }

}
