package com.aurora.d20_35_app.ActivitiesInstrumentedTest;

import android.content.Context;

import com.aurora.main.views.MainMenuActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import static org.junit.Assert.assertEquals;

public class d2035AppViewInstrumentedTest {

    @Rule
    public ActivityTestRule<MainMenuActivity> mainActivityActivityTestRule = new ActivityTestRule<MainMenuActivity>(MainMenuActivity.class);
    private MainMenuActivity MainMenuActivity = null;

    @Before
    public void setUp() throws Exception {
        MainMenuActivity = mainActivityActivityTestRule.getActivity();
    }

    @After
    public void tearDown() throws Exception {
        MainMenuActivity = null;
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
