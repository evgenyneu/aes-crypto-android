package com.evgenii.aescrypto.test;

import android.test.AndroidTestCase;
import android.webkit.WebView;

import com.evgenii.aescrypto.JsRunner;

public class JsRunnerTest extends AndroidTestCase {
	protected JsRunner mJsRunner;

	@Override
	protected void setUp() {
		mJsRunner = new JsRunner(mContext);
	}

	public void testRun() {
		assertTrue(true);
	}

	public void testGetWebView_shouldCreateWebView() {
		WebView webView = mJsRunner.getWebView();
		assertNotNull(webView);
	}

	public void testAddInitialJs() {
		mJsRunner.addInitialJs("function first(){};");
		mJsRunner.addInitialJs("function second(){};");
		assertEquals(2, mJsRunner.getInitialJs().size());
		assertEquals("function first(){};", mJsRunner.getInitialJs().get(0));
	}
}
