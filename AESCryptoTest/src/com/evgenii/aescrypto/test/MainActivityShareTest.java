package com.evgenii.aescrypto.test;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.EditText;

import com.evgenii.aescrypto.MainActivity;
import com.evgenii.aescrypto.R;

public class MainActivityShareTest extends ActivityUnitTestCase<MainActivity> {

	private Intent mLaunchIntent;

	public MainActivityShareTest() {
		super(MainActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mLaunchIntent = new Intent(getInstrumentation().getTargetContext(), MainActivity.class);

	}

	@MediumTest
	public void test_enterMessageAndPassword_clickEncrypt_share() {
		startActivity(mLaunchIntent, null, null);
		final EditText messageEdit = (EditText) getActivity().findViewById(R.id.message);
		messageEdit.setText("My Message");

		final EditText passwordEdit = (EditText) getActivity().findViewById(R.id.password);
		passwordEdit.setText("Tech Bubble");

		// getActivity().findViewById(R.id.action_encrypt).performClick();
	}
}
