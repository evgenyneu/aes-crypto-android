package com.evgenii.aescrypto;

import java.io.IOException;
import java.util.ArrayList;

import android.test.AndroidTestCase;

import com.evgenii.aescrypto.JsEncryptor;
import com.evgenii.aescrypto.mocks.AssetsFileReaderMock;
import com.evgenii.aescrypto.mocks.JsEvaluatorMock;
import com.evgenii.jsevaluator.interfaces.JsCallback;

public class JSEncryptorTests extends AndroidTestCase {
	JsEncryptor mJsEncryptor;
	AssetsFileReaderMock mAssetsFileReaderMock;
	JsEvaluatorMock mJsEvaluatorMock;

	@Override
	protected void setUp() {
		mAssetsFileReaderMock = new AssetsFileReaderMock();
		mJsEvaluatorMock = new JsEvaluatorMock();
		mJsEncryptor = new JsEncryptor(mAssetsFileReaderMock, mJsEvaluatorMock);
	}

	public void testConcatenateScripts() {
		mJsEncryptor.getScripts().add("test script one");
		mJsEncryptor.getScripts().add("test script two");
		final String result = mJsEncryptor.concatenateScripts();
		assertEquals("test script one; test script two; ", result);
	}

	public void testDecrypt() {
		final JsCallback callback = new JsCallback() {
			@Override
			public void onResult(final String resultValue) {
			}
		};

		mJsEncryptor.decrypt("decrypted text", "test password", callback);

		assertEquals(1, mJsEvaluatorMock.mEvaluateCallbacks.size());
		assertSame(callback, mJsEvaluatorMock.mEvaluateCallbacks.get(0));

		assertEquals(1, mJsEvaluatorMock.mEvaluatedScripts.size());
		assertEquals("aesCrypto.decrypt", mJsEvaluatorMock.mEvaluatedScripts.get(0));

		assertEquals(2, mJsEvaluatorMock.mEvaluateArguments.length);
		assertEquals("decrypted text", mJsEvaluatorMock.mEvaluateArguments[0]);
		assertEquals("test password", mJsEvaluatorMock.mEvaluateArguments[1]);
	}

	public void testEncrypt() {
		final JsCallback callback = new JsCallback() {
			@Override
			public void onResult(final String resultValue) {
			}
		};

		mJsEncryptor.encrypt("test text", "test password", callback);

		assertEquals(1, mJsEvaluatorMock.mEvaluateCallbacks.size());
		assertSame(callback, mJsEvaluatorMock.mEvaluateCallbacks.get(0));

		assertEquals(1, mJsEvaluatorMock.mEvaluatedScripts.size());
		assertEquals("aesCrypto.encrypt", mJsEvaluatorMock.mEvaluatedScripts.get(0));

		assertEquals(2, mJsEvaluatorMock.mEvaluateArguments.length);
		assertEquals("test text", mJsEvaluatorMock.mEvaluateArguments[0]);
		assertEquals("test password", mJsEvaluatorMock.mEvaluateArguments[1]);
	}

	public void testIsEncrypted_no() {
		assertFalse(mJsEncryptor.isEncrypted("not encrypted"));
	}

	public void testIsEncrypted_noWhenNull() {
		assertFalse(mJsEncryptor.isEncrypted(null));
	}

	public void testIsEncrypted_yes() {
		assertTrue(mJsEncryptor.isEncrypted(" AESCryptoV101319d  "));
	}

	public void testReadScripts() throws IOException {
		mJsEncryptor.readScripts();

		final ArrayList<String> scripts = mJsEncryptor.getScripts();
		assertEquals(2, scripts.size());
		assertEquals("javascript/crypto_js.js script", scripts.get(0));
		assertEquals("javascript/aes_crypto.js script", scripts.get(1));
	}
}
