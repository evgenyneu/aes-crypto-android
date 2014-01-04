package com.evgenii.aescrypto;

import android.util.Log;
import android.webkit.JavascriptInterface;

/**
 * Passed in addJavascriptInterface of WebView to allow web views's JS execute
 * Java code
 */
public class JavaScriptInterface {
	public String myData = "Data from Java";

	@JavascriptInterface
	public void sendResult(String result) {
		Log.d("ii", result);
	}

	@JavascriptInterface
	public String getString() {
		return myData;
	}

	@JavascriptInterface
	public void initialJsExecuted() {
	}
}
