package com.evgenii.aescrypto.test;

import android.test.AndroidTestCase;

import com.evgenii.aescrypto.Encrypt;
import com.evgenii.eascrypto.test.mocks.ClipboardMock;
import com.evgenii.eascrypto.test.mocks.JsEncryptorMock;
import com.evgenii.eascrypto.test.mocks.MainActivityMock;

public class EncryptTests extends AndroidTestCase {
	protected Encrypt mEncrypt;
	protected MainActivityMock mMainActivityMock;
	protected JsEncryptorMock mJsEncryptorMock;
	protected ClipboardMock mClipboardMock;

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		mMainActivityMock = new MainActivityMock();
		mJsEncryptorMock = new JsEncryptorMock();
		mClipboardMock = new ClipboardMock();

		mEncrypt = new Encrypt(mMainActivityMock, mJsEncryptorMock, mClipboardMock);
	}

	public void testEncryptAndUpdate_encryptsMessageWithPassword() {
		mMainActivityMock.mTestTrimmedMessage = "message to encrypt";
		mMainActivityMock.mTestTrimmedPassword = "test password one two";
		mEncrypt.encryptAndUpdate();
		assertEquals("message to encrypt", mJsEncryptorMock.mTestEncryptText);
		assertEquals("test password one two", mJsEncryptorMock.mTestEncryptPassword);
	}

	public void testEncryptAndUpdate_receivesEncryptedMessage() {
		mEncrypt.encryptAndUpdate();
		mJsEncryptorMock.mTestEncryptCallback.onResult("Encrypted test message :)");
		assertEquals("Encrypted test message :)", mClipboardMock.mTestClipboardContent);
		assertTrue(mMainActivityMock.mTestOptionsMenuInvalidated);
		assertTrue(mEncrypt.getJustCopied());
	}

	public void testIsEncryptable_NO_hasNoMessage() {
		mMainActivityMock.mTestHasMessage = false;
		mMainActivityMock.mTestHasPassword = true;
		mMainActivityMock.mTestIsBusy = false;
		assertFalse(mEncrypt.isEncryptable());
	}

	public void testIsEncryptable_NO_hasNoPassword() {
		mMainActivityMock.mTestHasMessage = true;
		mMainActivityMock.mTestHasPassword = false;
		mMainActivityMock.mTestIsBusy = false;
		assertFalse(mEncrypt.isEncryptable());
	}

	public void testIsEncryptable_NO_isBusy() {
		mMainActivityMock.mTestHasMessage = true;
		mMainActivityMock.mTestHasPassword = true;
		mMainActivityMock.mTestIsBusy = true;
		assertFalse(mEncrypt.isEncryptable());
	}

	public void testIsEncryptable_YES() {
		mMainActivityMock.mTestHasMessage = true;
		mMainActivityMock.mTestHasPassword = true;
		mMainActivityMock.mTestIsBusy = false;
		assertTrue(mEncrypt.isEncryptable());
	}
}