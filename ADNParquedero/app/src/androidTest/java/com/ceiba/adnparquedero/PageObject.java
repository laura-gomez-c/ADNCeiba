package com.ceiba.adnparquedero;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.NavigationViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


public class PageObject {

    public void sleep(int seconds) throws InterruptedException {
        Thread.sleep(seconds * 1000);
    }

    public void navigate(int idActivity, int idFragment) {
        onView(withId(idActivity)).perform(NavigationViewActions.navigateTo(idFragment));
    }

    public void writeEdit(int id, String text) {
        ViewInteraction editText = Espresso.onView(withId(id));
        editText.perform(replaceText(text));
    }

    public void clickButton(int idButton) {
        onView(withId(idButton)).perform(click());
    }

    public void clickRecycleViewItemWithTitle(int recycleView, String text) {
        onView(withId(recycleView)).perform(RecyclerViewActions.actionOnItem(hasDescendant(withText(text)), ViewActions.click()));

    }

    public void matchToast(int idMessage) {

        onView(withText(idMessage)).inRoot(new ToastMatcher())
                .check(ViewAssertions.matches(isDisplayed()));
    }

    public void matchEdit(int idEdit, String message) {
        onView(withId(idEdit)).check(ViewAssertions.matches(withText(message)));
    }


}