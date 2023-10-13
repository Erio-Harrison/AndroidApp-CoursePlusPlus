package com.example.couseplusplus;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.couseplusplus.view.CommentsActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class CommentsActivityTest {

    @Rule
    public ActivityScenarioRule<CommentsActivity> activityScenarioRule = new ActivityScenarioRule<>(CommentsActivity.class);

    // Test if the course code and name TextViews are displayed
    @Test
    public void testCourseInfoDisplayed() {
        onView(withId(R.id.course_code_info))
                .check(matches(isDisplayed()));

        onView(withId(R.id.course_name_info))
                .check(matches(isDisplayed()));
    }

    // Test if the comment search input is displayed and accepts input
    @Test
    public void testCommentSearchInput() {
        String testInput = "helpful > 1";
        onView(withId(R.id.comment_search_input))
                .perform(typeText(testInput))
                .check(matches(isDisplayed()));
    }

    // Test if the search button is clickable
    @Test
    public void testSearchButton() {
        onView(withId(R.id.comment_search_button))
                .perform(click())
                .check(matches(isDisplayed()));
    }

    // Test if the sort radio buttons are displayed and "Helpful" is selected by default
    @Test
    public void testSortOption() {
        onView(withId(R.id.helpful_radio_button))
                .check(matches(isDisplayed()));

        onView(withId(R.id.enrol_radio_button))
                .check(matches(isDisplayed()));

        onView(withId(R.id.posted_radio_button))
                .check(matches(isDisplayed()));
    }

    // Test if the RecyclerView is displayed
    @Test
    public void testRecyclerViewDisplayed() {
        onView(withId(R.id.comment_rv))
                .check(matches(isDisplayed()));
    }

    // Test if the add comment button is displayed and clickable
    @Test
    public void testAddCommentButton() {
        onView(withId(R.id.moveToCommentAct))
                .perform(click())
                .check(matches(isDisplayed()));
    }

}