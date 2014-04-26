package com.evgenii.aescrypto.test;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;

import com.evgenii.aescrypto.MainActivity;

public class DecryptLogicTests extends ActivityInstrumentationTestCase2<MainActivity> {
	protected Intent mIntent;
	protected MainActivity mActivity;

	public DecryptLogicTests() {
		super(MainActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mActivity = getActivity();
	}

	public void testGetTextToDecrypt() {
		mActivity.mClipboard.set("AESCryptoV10 ˁ˚ᴥ˚ˀ");
		mActivity.storeTextToDecrypt();
		assertEquals("AESCryptoV10 ˁ˚ᴥ˚ˀ", mActivity.mTextToDecrypt);
	}
}
