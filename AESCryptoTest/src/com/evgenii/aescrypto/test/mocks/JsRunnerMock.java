package com.evgenii.aescrypto.test.mocks;

import com.evgenii.aescrypto.interfaces.JsRunnerCallbackInterface;

public class JsRunnerMock implements JsRunnerCallbackInterface {
	public boolean mAllPendingExecuted;

	@Override
	public void executeAllPendingJs() {
		mAllPendingExecuted = true;
	}
}
