package com.evgenii.aescrypto;

import java.util.ArrayList;

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
	private boolean mInitialJsEvaluationStarted;
	private boolean mInitialJsEvaluationFinished;
	private JavaScriptInterface mJsInterface;
	private final static String JS_NAMESPACE = "AESCrypto";
	private final static String JS_RESULT_FUNCTION = "result";
	private final static String INITIAL_JS_EXECUTED_CALLBACK = JS_NAMESPACE
			+ ".initialJsExecuted(); ";

	private final ArrayList<JsFunctionCall> mPendingJsCalls = new ArrayList<JsFunctionCall>();

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
		if (mInitialJsEvaluationStarted)
			throw new InitialJsHasAlreadyBeenRun();

		mInitialJSConcatenated += " " + js;
	}

	public boolean getInitialJsEvaluationStarted() {
		return mInitialJsEvaluationStarted;
	}

	public boolean getInitialJsEvaluationFinished() {
		return mInitialJsEvaluationFinished;
	}

	public void runInitialJs() {
		mInitialJsEvaluationStarted = true;
		getWebView().loadUrl(getCompleteInitialJsToEvaluate());
	}

	public String getCompleteInitialJsToEvaluate() {
		return String.format("javascript: %s %s", getInitialJSConcatenated(),
				INITIAL_JS_EXECUTED_CALLBACK);
	}

	private void initWebView() {
		if (mWebView != null)
			return;

		mWebView = new WebView(mContext);
		mWebView.loadData("", "text/html", null);
		mJsInterface = new JavaScriptInterface(this);
		mWebView.addJavascriptInterface(mJsInterface, JS_NAMESPACE);
	}

	public ArrayList<JsFunctionCall> getPendingJsCalls() {
		return mPendingJsCalls;
	}

	public void runJsFunction(String name, String param) {
		final ArrayList<Object> params = new ArrayList<Object>();
		params.add(param);

		getPendingJsCalls().add(new JsFunctionCall(name, params));
	}

	public void executeAllPendingJs() {
		if (mPendingJsCalls.size() == 0)
			return;

		while (mPendingJsCalls.size() > 0) {
			final JsFunctionCall jsFunctionCall = mPendingJsCalls
					.get(mPendingJsCalls.size() - 1);

			mPendingJsCalls.remove(mPendingJsCalls.size() - 1);

			final String js = JsRunner.getJsForFunctionCall(jsFunctionCall
					.toString());

			getWebView().loadUrl(js);
		}
	}

	public static String getJsForFunctionCall(String functionCallStr) {
		return String.format("javascript: %s.%s(%s);", JS_NAMESPACE,
				JS_RESULT_FUNCTION, functionCallStr);
	}

	@Override
	public void initalJsEvaluationHasFinished() {
		mInitialJsEvaluationFinished = true;
		executeAllPendingJs();
	}
}
