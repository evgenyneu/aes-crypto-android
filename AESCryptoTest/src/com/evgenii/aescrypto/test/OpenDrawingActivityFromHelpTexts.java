package com.evgenii.aescrypto.test;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.ImageView;

import com.evgenii.aescrypto.DrawingActivity;
import com.evgenii.aescrypto.R;

public class OpenDrawingActivityFromHelpTexts extends
		ActivityInstrumentationTestCase2<DrawingActivity> {
	public DrawingActivity mActivity;

	public OpenDrawingActivityFromHelpTexts() {
		super(DrawingActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mActivity = getActivity();
	}

	@MediumTest
	public void testOpenDrawingActivity() throws InterruptedException {
		final ImageView drawingView = (ImageView) mActivity.findViewById(R.id.imageViewDrawing);
		assertTrue(drawingView.getDrawable().getIntrinsicWidth() > 0);
	}
}
