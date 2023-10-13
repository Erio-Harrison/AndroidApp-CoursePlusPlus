package com.example.couseplusplus;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;




import com.example.couseplusplus.view.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class AddCommentActivityTest {

    @Rule
    public ActivityScenarioRule<AddComment> activityScenarioRule = new ActivityScenarioRule<>(AddComment.class);

    // Check if the comment title is displayed
    @Test
    public void testCommentTitleDisplayed() {
        onView(withId(R.id.addCommentTitle))
                .check(matches(isDisplayed()));
    }

    // Check if the comment space is displayed and accepts input
    @Test
    public void testCommentSpaceAcceptsInput() {
        String testInput = "This is a test comment.";
        onView(withId(R.id.comment_space))
                .perform(typeText(testInput), closeSoftKeyboard())
                .check(matches(withText(testInput)));
    }

    // Check if the year selector spinner is displayed and accepts selection
    @Test
    public void testYearSelector() {
        onView(withId(R.id.selectYear))
                .perform(click());
        onData(allOf(is(instanceOf(String.class)), is("2022")))
                .perform(click())
                .check(matches(isDisplayed()));
    }

    // Check if the semester selector spinner is displayed and accepts selection
    @Test
    public void testSemesterSelector() {
        onView(withId(R.id.selectSemester))
                .perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Semester 1")))
                .perform(click())
                .check(matches(isDisplayed()));
    }

    // Test clicking the post comment button
    @Test
    public void testPostCommentButton() {
        onView(withId(R.id.post_comment))
                .perform(click())
                .check(matches(isDisplayed()));
    }

}
