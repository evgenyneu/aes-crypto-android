package com.evgenii.aescrypto;

import java.util.ArrayList;

import android.content.Context;
import android.webkit.WebView;

/**
 * Executes JavaScript code. Loads JS code. Calls a JavaScript function. Allows
 * to pass an argument to function call. Stores the function's return value.
 */
public class JsRunner {
	private final Context mContext;
	private WebView mWebView;
	private ArrayList<String> mInitialJS;

	public JsRunner(Context context) {
		mContext = context;
	}

	public WebView getWebView() {
		initWebView();
		return mWebView;
	}

	public ArrayList<String> getInitialJs() {
		if (mInitialJS == null) {
			mInitialJS = new ArrayList<String>();
		}
		return mInitialJS;
	}

	public void addInitialJs(String js) {
		ArrayList<String> initialJs = getInitialJs();
		initialJs.add(js);
	}

	private void initWebView() {
		if (mWebView != null)
			return;

		mWebView = new WebView(mContext);
		mWebView.loadData("", "text/html", null);
	}

}
