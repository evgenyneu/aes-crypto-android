package com.evgenii.eascrypto.test.mocks;

import com.evgenii.aescrypto.interfaces.JsEncryptorInterface;
import com.evgenii.jsevaluator.interfaces.JsCallback;

public class JsEncryptorMock implements JsEncryptorInterface {

	@Override
	public void decrypt(String text, String password, JsCallback callback) {
		// TODO Auto-generated method stub

	}

	@Override
	public void encrypt(String text, String password, JsCallback callback) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isEncrypted(String text) {
		// TODO Auto-generated method stub
		return false;
	}

}
