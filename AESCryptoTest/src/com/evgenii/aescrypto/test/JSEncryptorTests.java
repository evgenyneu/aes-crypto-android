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

	public void testEvaluateScripts() {
		mJsEncryptor.getScripts().add("test script one");
		mJsEncryptor.getScripts().add("test script two");
		mJsEncryptor.evaluateScripts();
		assertEquals(2, mJsEvaluatorMock.mEvaluatedScripts.size());
		assertEquals("test script one", mJsEvaluatorMock.mEvaluatedScripts.get(0));
		assertEquals("test script two", mJsEvaluatorMock.mEvaluatedScripts.get(1));
	}

	public void testReadScripts() throws IOException {
		mJsEncryptor.readScripts();

		final ArrayList<String> scripts = mJsEncryptor.getScripts();
		assertEquals(7, scripts.size());
		assertEquals("javascript/crypto-js/core.js script", scripts.get(0));
		assertEquals("javascript/crypto-js/enc-base64.js script", scripts.get(1));
		assertEquals("javascript/aes_crypto.js script", scripts.get(6));
	}
}
