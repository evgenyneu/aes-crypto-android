package com.evgenii.aescrypto;

import android.util.Log;
import android.webkit.JavascriptInterface;

public class JavaScriptInterface {
	@JavascriptInterface
	public void sendResult(String result) {
		Log.d("ii", result);
	}
}
