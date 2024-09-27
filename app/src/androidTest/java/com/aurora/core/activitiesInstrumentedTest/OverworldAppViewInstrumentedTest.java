package com.aurora.core.activitiesInstrumentedTest;

import static org.junit.Assert.assertEquals;

import android.content.Context;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.aurora.main.views.MainMenuActivity;

public class OverworldAppViewInstrumentedTest {

    @Rule
    public ActivityTestRule<MainMenuActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainMenuActivity.class);
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
        assertEquals("com.aurora.overworld_app", appContext.getPackageName());
    }

    @Test
    public void onCreateTest() {

    }

}
