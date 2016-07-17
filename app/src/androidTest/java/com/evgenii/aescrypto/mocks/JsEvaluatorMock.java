package com.evgenii.aescrypto.mocks;

import android.webkit.WebView;

import java.util.ArrayList;

import com.evgenii.jsevaluator.interfaces.JsCallback;
import com.evgenii.jsevaluator.interfaces.JsEvaluatorInterface;

public class JsEvaluatorMock implements JsEvaluatorInterface {
	public ArrayList<String> mEvaluatedScripts = new ArrayList<String>();
	public ArrayList<JsCallback> mEvaluateCallbacks = new ArrayList<JsCallback>();
	public Object[] mEvaluateArguments = null;

	@Override
	public void callFunction(String jsCode, JsCallback callabck, String script,
			Object... jsArguments) {
		mEvaluateCallbacks.add(callabck);
		mEvaluatedScripts.add(script);
		mEvaluateArguments = jsArguments;
	}

	@Override
	public void evaluate(String script) {
		mEvaluatedScripts.add(script);
		mEvaluateCallbacks.add(null);
	}

	@Override
	public void evaluate(String script, JsCallback callback) {
		mEvaluatedScripts.add(script);
		mEvaluateCallbacks.add(callback);
	}

	@Override
	public void destroy() {

	}

	@Override
	public WebView getWebView() {
		return null;
	}

}
