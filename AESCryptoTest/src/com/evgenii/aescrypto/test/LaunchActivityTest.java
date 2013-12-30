package com.evgenii.aescrypto.test;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.Button;

import com.evgenii.aescrypto.MainActivity;
import com.evgenii.aescrypto.NextActivity;
import com.evgenii.aescrypto.R;

public class LaunchActivityTest extends ActivityUnitTestCase<MainActivity> {
	private Intent mLaunchIntent;

	public LaunchActivityTest() {
		super(MainActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mLaunchIntent = new Intent(getInstrumentation().getTargetContext(),
				MainActivity.class);
		startActivity(mLaunchIntent, null, null);
	}

	public void testPreconditions() {
		assertNotNull("mLaunchIntent is null", mLaunchIntent);
	}

	@MediumTest
	public void testNextActivityWasLaunchedWithIntent() {
		final Button launchNextButton = (Button) getActivity().findViewById(
				R.id.launch_next_activity_button);
		launchNextButton.performClick();

		final Intent launchIntent = getStartedActivityIntent();
		assertNotNull("Intent was null", launchIntent);

		final String payload = launchIntent
				.getStringExtra(NextActivity.EXTRAS_PAYLOAD_KEY);

		assertEquals("Payload is empty", "Hello!", payload);
	}
}
