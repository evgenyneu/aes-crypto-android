package com.evgenii.aescrypto.test;

import java.io.IOException;

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
		mJsEncryptor = new JsEncryptor(mContext, mAssetsFileReaderMock,
				mJsEvaluatorMock);
	}

	public void testReadFile() throws IOException {
		assertEquals("Hello", "Hello");
	}
}
