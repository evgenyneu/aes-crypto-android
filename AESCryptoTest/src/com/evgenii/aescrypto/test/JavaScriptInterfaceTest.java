package com.evgenii.aescrypto.test;

import android.test.AndroidTestCase;

import com.evgenii.aescrypto.JavaScriptInterface;

public class JavaScriptInterfaceTest extends AndroidTestCase {
	protected JavaScriptInterface mJavaScriptInterface;

	@Override
	protected void setUp() {
		mJavaScriptInterface = new JavaScriptInterface();
	}

	public void testInitialJsExecuted() {
		mJavaScriptInterface.initialJsExecuted();
	}

}
