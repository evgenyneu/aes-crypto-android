package com.evgenii.aescrypto;

import android.util.Log;
import android.webkit.JavascriptInterface;

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
}
