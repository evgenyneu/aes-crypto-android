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
		assertFalse(mJsRunnerMock.mInitalEvaluationHasFinished);
		mJavaScriptInterface.initialJsExecuted();
		assertTrue(mJsRunnerMock.mInitalEvaluationHasFinished);
	}

	public void testResult() {
		mJavaScriptInterface.result("test value", 12);
		assertEquals("test value", mJsRunnerMock.jsCallFinished_paramValue);
		assertEquals(Integer.valueOf(12),
				mJsRunnerMock.jsCallFinished_paramCallIndex);
	}
}
