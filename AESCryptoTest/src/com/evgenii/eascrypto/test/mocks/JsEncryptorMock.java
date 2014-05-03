package com.evgenii.eascrypto.test.mocks;

import com.evgenii.aescrypto.interfaces.JsEncryptorInterface;
import com.evgenii.jsevaluator.interfaces.JsCallback;

public class JsEncryptorMock implements JsEncryptorInterface {
	public boolean mTestIsEcrypted;

	public String mTestDecryptText;
	public String mTestDecryptPassword;
	public JsCallback mTestDecryptCallback;

	@Override
	public void decrypt(String text, String password, JsCallback callback) {
		mTestDecryptText = text;
		mTestDecryptPassword = password;
		mTestDecryptCallback = callback;
	}

	@Override
	public void encrypt(String text, String password, JsCallback callback) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isEncrypted(String text) {
		return mTestIsEcrypted;
	}

}
