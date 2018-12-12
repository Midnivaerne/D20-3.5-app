package com.aurora.d20_35_app.ActivitiesInstrumentedTest;

import androidx.test.rule.ActivityTestRule;

import com.aurora.d20_35_app.views.D2035appActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class d2035AppViewInstrumentedTest {

    @Rule
    public ActivityTestRule<D2035appActivity> mainActivityActivityTestRule = new ActivityTestRule<D2035appActivity>(D2035appActivity.class);
    private D2035appActivity D2035appActivity = null;

    @Before
    public void setUp() throws Exception {
        D2035appActivity = mainActivityActivityTestRule.getActivity();
    }

    @Test
    public void onCreateTest() {

    }

    @After
    public void tearDown() throws Exception {
        D2035appActivity = null;
    }

}
