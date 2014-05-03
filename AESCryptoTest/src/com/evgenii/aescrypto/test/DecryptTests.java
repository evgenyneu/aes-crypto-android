package com.evgenii.aescrypto.test;

import android.test.AndroidTestCase;

import com.evgenii.aescrypto.Decrypt;
import com.evgenii.eascrypto.test.mocks.ClipboardMock;
import com.evgenii.eascrypto.test.mocks.JsEncryptorMock;
import com.evgenii.eascrypto.test.mocks.MainActivityMock;

public class DecryptTests extends AndroidTestCase {
	protected Decrypt mDecrypt;
	protected MainActivityMock mMainActivityMock;
	protected JsEncryptorMock mJsEncryptorMock;
	protected ClipboardMock mClipboardMock;

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		mMainActivityMock = new MainActivityMock();
		mJsEncryptorMock = new JsEncryptorMock();
		mClipboardMock = new ClipboardMock();

		mDecrypt = new Decrypt(mMainActivityMock, mJsEncryptorMock, mClipboardMock);
	}

	public void testGetTextToDecrypt() {
		mClipboardMock.set("AESCryptoV10 ˁ˚ᴥ˚ˀ");
		mJsEncryptorMock.mTestIsEcrypted = true;
		mDecrypt.storeTextToDecrypt();
		assertEquals("AESCryptoV10 ˁ˚ᴥ˚ˀ", mDecrypt.getTextToDecrypt());
	}
}
