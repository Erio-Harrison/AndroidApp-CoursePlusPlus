package com.example.couseplusplus;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import androidx.test.espresso.action.ViewActions;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.couseplusplus.view.Login;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginActivityTest {

    @Rule
    public ActivityScenarioRule<Login> activityRule = new ActivityScenarioRule<>(Login.class);

    @Test
    public void testEmailInput() {
        // Check if the email input field is present and type into it
        onView(ViewMatchers.withId(R.id.email))
                .check(matches(ViewMatchers.isDisplayed()))
                .perform(ViewActions.typeText("test@example.com"), ViewActions.closeSoftKeyboard());
    }

    @Test
    public void testPasswordInput() {
        // Check if the password input field is present and type into it
        onView(ViewMatchers.withId(R.id.password))
                .check(matches(ViewMatchers.isDisplayed()))
                .perform(ViewActions.typeText("password123"), ViewActions.closeSoftKeyboard());
    }

    @Test
    public void testLoginButtonClick() {
        // Check if the login button is present and click on it
        onView(ViewMatchers.withId(R.id.btn_login))
                .check(matches(ViewMatchers.isDisplayed()))
                .perform(ViewActions.click());
    }

    @Test
    public void testRegisterNowClick() {
        // Check if the "Click to Register" TextView is present and click on it
        onView(ViewMatchers.withId(R.id.RegisterNow))
                .check(matches(ViewMatchers.isDisplayed()))
                .perform(ViewActions.click());
    }
}