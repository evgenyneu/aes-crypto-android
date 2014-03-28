package com.evgenii.aescrypto.test;

import java.io.IOException;
import java.util.ArrayList;

import android.test.AndroidTestCase;

import com.evgenii.aescrypto.JsEncryptor;
import com.evgenii.eascrypto.test.mocks.AssetsFileReaderMock;
import com.evgenii.eascrypto.test.mocks.JsEvaluatorMock;

public class JSEncryptorTests extends AndroidTestCase {
	JsEncryptor mJsEncryptor;
	AssetsFileReaderMock mAssetsFileReaderMock;
	JsEvaluatorMock mJsEvaluatorMock;

	@Override
	protected void setUp() {
		mAssetsFileReaderMock = new AssetsFileReaderMock();
		mJsEvaluatorMock = new JsEvaluatorMock();
		mJsEncryptor = new JsEncryptor(mContext, mAssetsFileReaderMock, mJsEvaluatorMock);
	}

	public void testReadScripts() throws IOException {
		mJsEncryptor.ReadScripts();

		final ArrayList<String> scripts = mJsEncryptor.scripts();
		assertEquals(7, scripts.size());
		assertEquals(scripts.get(0), "javascript/crypto-js/core.js script");
		assertEquals(scripts.get(1), "javascript/crypto-js/enc-base64.js script");
		assertEquals(scripts.get(6), "javascript/aes_crypto.js script");
	}
}
