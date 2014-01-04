package com.evgenii.aescrypto;

import java.util.ArrayList;

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
	private ArrayList<String> mInitialJS;
	private boolean mIsInitialJsEvaluated;

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

	public void addInitialJs(String js) throws InitialJsHasAlreadyBeenRun {
		if (mIsInitialJsEvaluated)
			throw new InitialJsHasAlreadyBeenRun();
		ArrayList<String> initialJs = getInitialJs();
		initialJs.add(js);
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
