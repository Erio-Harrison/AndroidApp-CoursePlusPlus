package com.example.couseplusplus;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.couseplusplus.view.Register;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class RegisterActivityTest {

    @Rule
    public ActivityScenarioRule<Register> activityScenarioRule = new ActivityScenarioRule<>(Register.class);

    // Test if the email input field is displayed and accepts input
    @Test
    public void testEmailInput() {
        onView(withId(R.id.email))
                .perform(typeText("test@email.com"))
                .check(matches(withHint(R.string.email)));
    }

    // Test if the password input field is displayed and accepts input
    @Test
    public void testPasswordInput() {
        onView(withId(R.id.password))
                .perform(typeText("password123"))
                .check(matches(withHint(R.string.password)));
    }

    // Test if the progress bar is initially hidden
    @Test
    public void testProgressBarInitiallyHidden() {
        onView(withId(R.id.progressbar))
                .check(matches(isDisplayed()));
    }

    // Test if the registration button is clickable
    @Test
    public void testRegisterButton() {
        onView(withId(R.id.btn_register))
                .perform(click())
                .check(matches(isDisplayed()));
    }

    // Test if the "click to login" TextView is displayed
    @Test
    public void testLoginPromptDisplayed() {
        onView(withId(R.id.loginNow))
                .check(matches(isDisplayed()))
                .check(matches(withHint(R.string.click_to_login)));
    }

}
