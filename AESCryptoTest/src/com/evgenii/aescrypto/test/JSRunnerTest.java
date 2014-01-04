package com.evgenii.aescrypto.test;

import android.test.AndroidTestCase;
import android.webkit.WebView;

import com.evgenii.aescrypto.JsRunner;
import com.evgenii.aescrypto.exceptions.InitialJsHasAlreadyBeenRun;

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

	public void testAddInitialJs() throws InitialJsHasAlreadyBeenRun {
		mJsRunner.addInitialJs("function first(){};");
		mJsRunner.addInitialJs("function second(){};");
		assertEquals(" function first(){}; function second(){};",
				mJsRunner.getInitialJSConcatenated());
	}

	public void testAddInitialJs_canNotAddAfterRunning() {
		try {
			mJsRunner.runInitialJs();
			mJsRunner.addInitialJs("function second(){};");
		} catch (InitialJsHasAlreadyBeenRun e) {
		}
	}

	public void testInitialJsShouldNotBeEvaluatedByDefault() {
		assertFalse(mJsRunner.getIsInitialJsEvaluated());
	}

	public void testRunInitialJs_markInitialJsAsEvaluated() {
		mJsRunner.runInitialJs();
		assertTrue(mJsRunner.getIsInitialJsEvaluated());
	}
}
