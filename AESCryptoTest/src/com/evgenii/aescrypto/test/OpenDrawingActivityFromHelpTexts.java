package com.evgenii.aescrypto.test;

import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.MediumTest;
import android.view.View;

import com.evgenii.aescrypto.DrawingActivity;
import com.evgenii.aescrypto.HelpActivity;
import com.evgenii.aescrypto.R;

public class OpenDrawingActivityFromHelpTexts extends
		ActivityInstrumentationTestCase2<HelpActivity> {
	public HelpActivity mHelpActivity;

	public OpenDrawingActivityFromHelpTexts() {
		super(HelpActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mHelpActivity = getActivity();
	}

	@MediumTest
	public void testOpenDrawingActivity() throws InterruptedException {
		final ActivityMonitor drawingActivityMonitor = getInstrumentation().addMonitor(
				DrawingActivity.class.getName(), null, false);

		final View openDrawingButton = mHelpActivity.findViewById(R.id.action_view_drawing);
		TouchUtils.clickView(this, openDrawingButton);

		final DrawingActivity drawingActivity = (DrawingActivity) drawingActivityMonitor
				.waitForActivityWithTimeout(3000);

		assertNotNull("DrawingActivity is null", drawingActivity);
		assertEquals("Monitor for DrawingActivity has not been called", 1,
				drawingActivityMonitor.getHits());
		assertEquals("Activity is of wrong type", DrawingActivity.class, drawingActivity.getClass());

		// Go back to help activity
		final View homeButton = drawingActivity.findViewById(android.R.id.home);
		TouchUtils.clickView(this, homeButton);

		// Remove the ActivityMonitor
		getInstrumentation().removeMonitor(drawingActivityMonitor);
	}
}
