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
		final WebView webView = mJsRunner.getWebView();
		assertNotNull(webView);
	}

	public void testAddInitialJs() throws InitialJsHasAlreadyBeenRun {
		mJsRunner.addInitialJs("function first(){};");
		mJsRunner.addInitialJs("function second(){};");
		assertEquals(" function first(){}; function second(){};",
				mJsRunner.getInitialJSConcatenated());
	}

	public void testGetCompleteInitialJsToEvaluate()
			throws InitialJsHasAlreadyBeenRun {
		mJsRunner.addInitialJs("function first(){};");
		mJsRunner.addInitialJs("function second(){};");
		assertEquals(
				"javascript:  function first(){}; function second(){}; AESCrypto.initialJsExecuted(); ",
				mJsRunner.getCompleteInitialJsToEvaluate());
	}

	public void testAddInitialJs_canNotAddAfterRunning() {
		try {
			mJsRunner.runInitialJs();
			mJsRunner.addInitialJs("function second(){};");
		} catch (final InitialJsHasAlreadyBeenRun e) {
		}
	}

	public void testInitialJsShouldNotBeEvaluatedByDefault() {
		assertFalse(mJsRunner.getIsInitialJsEvaluated());
	}

	public void testRunInitialJs_markInitialJsAsEvaluated() {
		mJsRunner.runInitialJs();
		assertTrue(mJsRunner.getIsInitialJsEvaluated());
	}

	public void testRunJsFunction() {
		mJsRunner.runJsFunction("myFirstName", "param1");
		mJsRunner.runJsFunction("mySecondName", "param2");
		assertEquals(2, mJsRunner.getPendingJsCalls().size());
	}

	public void testExecuteAllPendingJs() {
		mJsRunner.executeAllPendingJs();
	}
}
