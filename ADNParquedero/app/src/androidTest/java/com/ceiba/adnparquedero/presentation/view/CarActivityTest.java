package com.ceiba.adnparquedero.presentation.view;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import com.ceiba.adnparquedero.PageObject;
import com.ceiba.adnparquedero.R;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class CarActivityTest {

    private PageObject pageObject;

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule =
            new ActivityTestRule<>(MainActivity.class, true, true);

    @Before
    public void before() {
        pageObject = new PageObject();
    }

    @Test
    public void registerCar() throws InterruptedException {

        pageObject.clickButton(R.id.button_register_car);
        pageObject.clickButton(R.id.button_register_car);
        pageObject.writeEdit(R.id.editTextLicense, "ABC123");
        pageObject.clickButton(R.id.button_register);


        //pageObject.matchToast(R.string.successfully_registered);
        pageObject.clickRecycleViewItemWithTitle(R.id.recycler_view_cars, "ABC123");
        //pageObject.writeEdit(R.id.editTextSearch, "Leanne");
        //pageObject.matchEdit(R.id.editTextSearch, "Leanne");
    }
}