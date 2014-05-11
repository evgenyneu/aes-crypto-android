package com.evgenii.aescrypto.test;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.ImageButton;

import com.evgenii.aescrypto.MainActivity;
import com.evgenii.aescrypto.R;

public class OpenHelpFromMainTests extends ActivityUnitTestCase<MainActivity> {
	public Intent mMainIntent;

	public OpenHelpFromMainTests() {
		super(MainActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mMainIntent = new Intent(getInstrumentation().getTargetContext(), MainActivity.class);
	}

	@MediumTest
	public void testHelpActivityWasLaunchedWithIntent() {
		startActivity(mMainIntent, null, null);
		final ImageButton openHelpButton = (ImageButton) getActivity().findViewById(
				R.id.helpImageButton);
		openHelpButton.performClick();

		final Intent launchIntent = getStartedActivityIntent();
		assertNotNull("Intent was null", launchIntent);
		assertEquals("com.evgenii.aescrypto.HelpActivity", launchIntent.getComponent()
				.getClassName());
		assertFalse(isFinishCalled());
	}
}
