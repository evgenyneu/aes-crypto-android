package com.evgenii.aescrypto;

import android.webkit.JavascriptInterface;

import com.evgenii.aescrypto.interfaces.JsRunnerCallbackInterface;

/**
 * Passed in addJavascriptInterface of WebView to allow web views's JS execute
 * Java code
 */
public class JavaScriptInterface {
	private final JsRunnerCallbackInterface mJsRunner;

	public JavaScriptInterface(JsRunnerCallbackInterface jsRunner) {
		mJsRunner = jsRunner;
	}

	@JavascriptInterface
	public void initialJsExecuted() {
		mJsRunner.executeAllPendingJs();
	}
}
