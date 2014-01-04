package com.evgenii.aescrypto;

import android.content.Context;
import android.webkit.WebView;

import com.evgenii.aescrypto.exceptions.InitialJsHasAlreadyBeenRun;

/**
 * Executes JavaScript code. Loads JS code. Calls a JavaScript function. Allows
 * to pass an argument to function call. Stores the function's return value.
 */
public class JsRunner {
	private final Context mContext;
	private WebView mWebView;
	private String mInitialJSConcatenated = "";
	private boolean mIsInitialJsEvaluated;

	public JsRunner(Context context) {
		mContext = context;
	}

	public WebView getWebView() {
		initWebView();
		return mWebView;
	}

	public String getInitialJSConcatenated() {
		return mInitialJSConcatenated;
	}

	public void addInitialJs(String js) throws InitialJsHasAlreadyBeenRun {
		if (mIsInitialJsEvaluated)
			throw new InitialJsHasAlreadyBeenRun();

		mInitialJSConcatenated += " " + js;
	}

	public boolean getIsInitialJsEvaluated() {
		return mIsInitialJsEvaluated;
	}

	public void runInitialJs() {
		mIsInitialJsEvaluated = true;
	}

	private void initWebView() {
		if (mWebView != null)
			return;

		mWebView = new WebView(mContext);
		mWebView.loadData("", "text/html", null);
	}

}
