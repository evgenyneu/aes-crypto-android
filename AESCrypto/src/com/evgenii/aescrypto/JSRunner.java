package com.evgenii.aescrypto;

import android.content.Context;
import android.webkit.WebView;

import com.evgenii.aescrypto.exceptions.InitialJsHasAlreadyBeenRun;
import com.evgenii.aescrypto.interfaces.JsRunnerCallbackInterface;

/**
 * Executes JavaScript code. Loads JS code. Calls a JavaScript function. Allows
 * to pass an argument to function call. Stores the function's return value.
 */
public class JsRunner implements JsRunnerCallbackInterface {
	private final Context mContext;
	private WebView mWebView;
	private String mInitialJSConcatenated = "";
	private boolean mIsInitialJsEvaluated;
	private JavaScriptInterface mJsInterface;
	private final static String JS_NAMESPACE = "AESCrypto";
	private final static String INITIAL_JS_EXECUTED_CALLBACK = JS_NAMESPACE
			+ ".initialJsExecuted(); ";

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
		getWebView().loadUrl(getCompleteInitialJsToEvaluate());
	}

	public String getCompleteInitialJsToEvaluate() {
		return "javascript: " + getInitialJSConcatenated() + " "
				+ INITIAL_JS_EXECUTED_CALLBACK;
	}

	private void initWebView() {
		if (mWebView != null)
			return;

		mWebView = new WebView(mContext);
		mWebView.loadData("", "text/html", null);
		mJsInterface = new JavaScriptInterface();
		mWebView.addJavascriptInterface(mJsInterface, JS_NAMESPACE);
	}

	public void runJsFunction(String name) {

	}

	public void executeAllPendingJs() {

	}
}
