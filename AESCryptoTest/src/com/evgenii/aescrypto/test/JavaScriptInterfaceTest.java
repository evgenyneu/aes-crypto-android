package com.evgenii.aescrypto.test;

import android.test.AndroidTestCase;

import com.evgenii.aescrypto.JavaScriptInterface;
import com.evgenii.aescrypto.test.mocks.JsRunnerMock;

public class JavaScriptInterfaceTest extends AndroidTestCase {
	protected JavaScriptInterface mJavaScriptInterface;
	protected JsRunnerMock mJsRunnerMock;

	@Override
	protected void setUp() {
		mJsRunnerMock = new JsRunnerMock();
		mJavaScriptInterface = new JavaScriptInterface(mJsRunnerMock);
	}

	public void testInitialJsExecuted_executesAllPendingJs() {
		mJavaScriptInterface.initialJsExecuted();
		assertTrue(mJsRunnerMock.mAllPendingExecuted);
	}
}
