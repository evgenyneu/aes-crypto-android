package com.evgenii.aescrypto.test;

import android.test.InstrumentationTestCase;

import com.evgenii.aescrypto.JSRunner;

public class JSRunnerTest extends InstrumentationTestCase {
	protected JSRunner mJSRunner;

	@Override
	protected void setUp() {
		mJSRunner = new JSRunner(getInstrumentation().getContext());
	}

	public void testRun() {
		assertTrue(true);
	}
}
