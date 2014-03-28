package com.evgenii.eascrypto.test.mocks;

import java.util.ArrayList;

import com.evgenii.jsevaluator.interfaces.JsCallback;
import com.evgenii.jsevaluator.interfaces.JsEvaluatorInterface;

public class JsEvaluatorMock implements JsEvaluatorInterface {
	public ArrayList<String> mEvaluatedScripts = new ArrayList<String>();
	public ArrayList<JsCallback> mEvaluateCallbacks = new ArrayList<JsCallback>();

	@Override
	public void callFunction(JsCallback callabck, String script, Object... jsArguments) {

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

}
