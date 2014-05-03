package com.evgenii.aescrypto.test;

import android.test.AndroidTestCase;

import com.evgenii.aescrypto.Decrypt;
import com.evgenii.eascrypto.test.mocks.MainActivityMock;

public class DecryptTests extends AndroidTestCase {
	protected Decrypt mDecrypt;
	protected MainActivityMock mMainActivityMock;

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		mMainActivityMock = new MainActivityMock();

		// mDecrypt = new Decrypt(mMainActivityMock, jsEncryptor, clipboard)
	}

	public void testGetTextToDecrypt() {
		// mActivity.mClipboard.set("AESCryptoV10 ˁ˚ᴥ˚ˀ");
		// mActivity.storeTextToDecrypt();
		// assertEquals("AESCryptoV10 ˁ˚ᴥ˚ˀ", mActivity.mTextToDecrypt);
	}
}
