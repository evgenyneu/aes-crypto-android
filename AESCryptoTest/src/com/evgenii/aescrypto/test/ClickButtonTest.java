package com.evgenii.aescrypto.test;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.test.suitebuilder.annotation.MediumTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.evgenii.aescrypto.MainActivity;
import com.evgenii.aescrypto.R;

public class ClickButtonTest extends
		ActivityInstrumentationTestCase2<MainActivity> {

	private MainActivity mMainActivity;
	private Button mClickMeButton;
	private TextView mInfoTextView;

	public ClickButtonTest() {
		super(MainActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		setActivityInitialTouchMode(true);

		mMainActivity = getActivity();

		mClickMeButton = (Button) mMainActivity
				.findViewById(R.id.click_me_button);

		mInfoTextView = (TextView) mMainActivity
				.findViewById(R.id.info_text_view);
	}

	public void testPreconditions() {
		assertNotNull("mMainActivity is null", mMainActivity);
		assertNotNull("mClickMeButton is null", mClickMeButton);
		assertNotNull("mInfoTextView is null", mInfoTextView);
	}

	@MediumTest
	public void testClickMeButton_layout() {
		final View decorView = mMainActivity.getWindow().getDecorView();

		ViewAsserts.assertOnScreen(decorView, mClickMeButton);

		final ViewGroup.LayoutParams layoutParams = mClickMeButton
				.getLayoutParams();
		assertNotNull(layoutParams);
		assertEquals(layoutParams.width,
				WindowManager.LayoutParams.MATCH_PARENT);
		assertEquals(layoutParams.height,
				WindowManager.LayoutParams.WRAP_CONTENT);
	}

	@MediumTest
	public void testInfoTextView_layout() {
		final View decorView = mMainActivity.getWindow().getDecorView();
		ViewAsserts.assertOnScreen(decorView, mInfoTextView);
		assertTrue(View.GONE == mInfoTextView.getVisibility());
	}

	@MediumTest
	public void testClickMeButton_clickButtonAndExpectInfoText() {
		String expectedInfoText = mMainActivity.getString(R.string.info_text);
		TouchUtils.clickView(this, mClickMeButton);
		assertTrue(View.VISIBLE == mInfoTextView.getVisibility());
		assertEquals(expectedInfoText, mInfoTextView.getText());
	}
}
