package com.example.couseplusplus;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.couseplusplus.view.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void ensureTextViewIsPresent() {
        onView(withId(R.id.user_details))
                .check(matches(isDisplayed()));

        onView(withId(R.id.user_details))
                .check(matches(withText("Hello World!")));
    }

    @Test
    public void ensureLogOutButtonIsPresent() {
        onView(withId(R.id.logout))
                .check(matches(isDisplayed()));

        onView(withId(R.id.logout))
                .check(matches(withText(R.string.log_out)));
    }

    @Test
    public void ensureSearchEditTextIsPresent() {
        onView(withId(R.id.course_search_input))
                .check(matches(isDisplayed()));
    }

    @Test
    public void ensureSearchButtonIsPresent() {
        onView(withId(R.id.course_search_button))
                .check(matches(isDisplayed()));

        onView(withId(R.id.course_search_button))
                .check(matches(withText("Search")));
    }

    @Test
    public void ensureRecyclerViewIsPresent() {
        onView(withId(R.id.course_rv))
                .check(matches(isDisplayed()));
    }
}
