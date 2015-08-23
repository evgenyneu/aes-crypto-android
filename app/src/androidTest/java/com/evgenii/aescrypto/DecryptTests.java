package com.evgenii.aescrypto;

import android.test.AndroidTestCase;

import com.evgenii.aescrypto.Decrypt;
import com.evgenii.aescrypto.mocks.JsEncryptorMock;
import com.evgenii.aescrypto.mocks.MainActivityMock;

public class DecryptTests extends AndroidTestCase {
	protected Decrypt mDecrypt;
	protected MainActivityMock mMainActivityMock;
	protected JsEncryptorMock mJsEncryptorMock;

	private void makeDecryptable() {
		mMainActivityMock.mTestIsBusy = false;
		mMainActivityMock.mTestHasPassword = true;
		mJsEncryptorMock.mTestIsEcrypted = true;
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		mMainActivityMock = new MainActivityMock();
		mJsEncryptorMock = new JsEncryptorMock();

		mDecrypt = new Decrypt(mMainActivityMock, mJsEncryptorMock);
	}

	public void testDecryptAndUpdate_decryptsTextWithThePassword() {
		makeDecryptable();

		mMainActivityMock.mTestTrimmedPassword = "test password";
		mMainActivityMock.mTestTrimmedMessage = "test text to decrypt";

		mDecrypt.decryptAndUpdate();

		assertEquals("test text to decrypt", mJsEncryptorMock.mTestDecryptText);
		assertEquals("test password", mJsEncryptorMock.mTestDecryptPassword);
	}

	public void testDecryptAndUpdate_doNotDecrypt_ifNotDecryptable() {
		mMainActivityMock.mTestHasPassword = false;
		mMainActivityMock.mTestTrimmedPassword = "test password";

		mDecrypt.decryptAndUpdate();

		assertNull(mJsEncryptorMock.mTestDecryptPassword);
	}

	public void testDecryptAndUpdate_doNotShowDecryptedText_ifDecryptionResultIsEmpty() {
		makeDecryptable();
		mMainActivityMock.mTestMessage = "Message before";

		mDecrypt.decryptAndUpdate();

		mJsEncryptorMock.mTestDecryptCallback.onResult("");
		assertEquals("Message before", mMainActivityMock.mTestMessage);
	}

	public void testDecryptAndUpdate_showsDecryptedText() {
		makeDecryptable();

		mDecrypt.decryptAndUpdate();

		mJsEncryptorMock.mTestDecryptCallback.onResult("test decrypted string");
		assertEquals("test decrypted string", mMainActivityMock.mTestMessage);
	}

	public void testIsDecryptable_NO_whenBusy() {
		mMainActivityMock.mTestIsBusy = true;
		mMainActivityMock.mTestHasPassword = true;
		mJsEncryptorMock.mTestIsEcrypted = true;
		assertFalse(mDecrypt.isDecryptable());
	}

	public void testIsDecryptable_NO_whenMessageIsNotEncrypted() {
		mMainActivityMock.mTestIsBusy = false;
		mMainActivityMock.mTestHasPassword = true;
		mJsEncryptorMock.mTestIsEcrypted = false;
		assertFalse(mDecrypt.isDecryptable());
	}

	public void testIsDecryptable_NO_withNoPassword() {
		mMainActivityMock.mTestIsBusy = false;
		mMainActivityMock.mTestHasPassword = false;
		mJsEncryptorMock.mTestIsEcrypted = true;
		assertFalse(mDecrypt.isDecryptable());
	}

	public void testIsDecryptable_YES() {
		mMainActivityMock.mTestIsBusy = false;
		mMainActivityMock.mTestHasPassword = true;
		mJsEncryptorMock.mTestIsEcrypted = true;
		assertTrue(mDecrypt.isDecryptable());
	}
}
