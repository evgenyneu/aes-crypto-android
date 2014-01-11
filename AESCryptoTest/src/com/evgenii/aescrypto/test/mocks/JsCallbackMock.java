package com.evgenii.aescrypto.test.mocks;

import com.evgenii.aescrypto.interfaces.JsCallback;

public class JsCallbackMock implements JsCallback {
	public String resultValue;

	@Override
	public void onResult(String value) {
		resultValue = value;
	}
}
