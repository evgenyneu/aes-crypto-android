package com.evgenii.aescrypto.interfaces;

/**
 * Used in JavaScriptInterface to interact with JsRunner
 */
public interface JsRunnerCallbackInterface {
	public void initalJsEvaluationHasFinished();

	public void jsCallFinished(String value, Integer callIndex);
}
