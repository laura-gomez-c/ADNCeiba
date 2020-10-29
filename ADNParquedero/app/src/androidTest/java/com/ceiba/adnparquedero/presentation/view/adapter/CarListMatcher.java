package com.ceiba.adnparquedero.presentation.view.adapter;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.matcher.BoundedMatcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class CarListMatcher {

    public static Matcher<RecyclerView.ViewHolder> withTitle(final String title) {
        return new BoundedMatcher(CarListAdapter.ViewHolder.class) {
            @Override
            protected boolean matchesSafely(Object item) {
                return ((CarListAdapter.ViewHolder) item).textLicensePlate.getText().toString().equalsIgnoreCase(title);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("view holder with title: " + title);
            }
        };
    }
}
