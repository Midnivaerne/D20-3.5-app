package com.aurora.d20_35_app.ActivitiesInstrumentedTest;

import android.content.Context;

import com.aurora.d20_35_app.views.D2035appActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import static org.junit.Assert.assertEquals;

public class d2035AppViewInstrumentedTest {

    @Rule
    public ActivityTestRule<D2035appActivity> mainActivityActivityTestRule = new ActivityTestRule<D2035appActivity>(D2035appActivity.class);
    private D2035appActivity D2035appActivity = null;

    @Before
    public void setUp() throws Exception {
        D2035appActivity = mainActivityActivityTestRule.getActivity();
    }

    @After
    public void tearDown() throws Exception {
        D2035appActivity = null;
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.aurora.d20_35_app", appContext.getPackageName());
    }

    @Test
    public void onCreateTest() {

    }

}
