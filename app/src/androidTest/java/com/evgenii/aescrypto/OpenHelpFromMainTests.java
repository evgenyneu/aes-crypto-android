package com.evgenii.aescrypto;

import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.MediumTest;
import android.view.View;
import android.widget.ImageButton;

import com.evgenii.aescrypto.HelpActivity;
import com.evgenii.aescrypto.MainActivity;
import com.evgenii.aescrypto.R;

public class OpenHelpFromMainTests extends ActivityInstrumentationTestCase2<MainActivity> {
	public MainActivity mMainActivity;

	public OpenHelpFromMainTests() {
		super(MainActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mMainActivity = getActivity();
	}

	@MediumTest
	public void testHelpActivityIsOpened() throws InterruptedException {
		final ActivityMonitor helpActivityMonitor = getInstrumentation().addMonitor(
				HelpActivity.class.getName(), null, false);

		final ImageButton openHelpButton = (ImageButton) mMainActivity
				.findViewById(R.id.helpImageButton);
		TouchUtils.clickView(this, openHelpButton);

		final HelpActivity helpActivity = (HelpActivity) helpActivityMonitor
				.waitForActivityWithTimeout(1000);

		assertNotNull("HelpActivity is null", helpActivity);
		assertEquals("Monitor for HelpActivity has not been called", 1,
				helpActivityMonitor.getHits());
		assertEquals("Activity is of wrong type", HelpActivity.class, helpActivity.getClass());

		// Go back to main activity
		final View homeButton = helpActivity.findViewById(android.R.id.home);
		TouchUtils.clickView(this, homeButton);

		// Remove the ActivityMonitor
		getInstrumentation().removeMonitor(helpActivityMonitor);
	}
}
