package com.evgenii.eascrypto.test.mocks;

import com.evgenii.aescrypto.interfaces.JsEncryptorInterface;
import com.evgenii.jsevaluator.interfaces.JsCallback;

public class JsEncryptorMock implements JsEncryptorInterface {
	public boolean mTestIsEcrypted;

	public String mTestDecryptText;
	public String mTestDecryptPassword;
	public JsCallback mTestDecryptCallback;

	public String mTestEncryptText;
	public String mTestEncryptPassword;
	public JsCallback mTestEncryptCallback;

	@Override
	public void decrypt(String text, String password, JsCallback callback) {
		mTestDecryptText = text;
		mTestDecryptPassword = password;
		mTestDecryptCallback = callback;
	}

	@Override
	public void encrypt(String text, String password, JsCallback callback) {
		mTestEncryptText = text;
		mTestEncryptPassword = password;
		mTestEncryptCallback = callback;
	}

	@Override
	public boolean isEncrypted(String text) {
		return mTestIsEcrypted;
	}

}
