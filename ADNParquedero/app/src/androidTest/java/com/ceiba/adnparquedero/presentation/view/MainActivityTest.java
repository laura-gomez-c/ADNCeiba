package com.ceiba.adnparquedero.presentation.view;

import androidx.test.rule.ActivityTestRule;

import com.ceiba.adnparquedero.PageObject;
import com.ceiba.adnparquedero.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class MainActivityTest {

    private PageObject pageObject;

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule =
            new ActivityTestRule<>(MainActivity.class, true, true);

    @Before
    public void before() {
        pageObject = new PageObject();
    }

    @Test
    public void openCarActivity() throws InterruptedException {
        pageObject.sleep(1);
        pageObject.clickButton(R.id.button_register_car);

        pageObject.sleep(2);

        pageObject.writeEdit(R.id.editTextSearch, "Leanne");
        pageObject.matchEdit(R.id.editTextSearch, "Leanne");
    }

    @Test
    public void openMotoActivity() throws InterruptedException {
        pageObject.sleep(1);
        pageObject.clickButton(R.id.button_register_moto);

        pageObject.sleep(2);

        pageObject.writeEdit(R.id.editTextSearch, "Leanne");
        pageObject.matchEdit(R.id.editTextSearch, "Leanne");
    }
}