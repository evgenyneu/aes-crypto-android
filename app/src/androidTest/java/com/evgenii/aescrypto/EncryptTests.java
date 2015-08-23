package com.evgenii.aescrypto;

import android.test.AndroidTestCase;

import com.evgenii.aescrypto.Encrypt;
import com.evgenii.aescrypto.mocks.ClipboardMock;
import com.evgenii.aescrypto.mocks.JsEncryptorMock;
import com.evgenii.aescrypto.mocks.MainActivityMock;

public class EncryptTests extends AndroidTestCase {
	protected Encrypt mEncrypt;
	protected MainActivityMock mMainActivityMock;
	protected JsEncryptorMock mJsEncryptorMock;
	protected ClipboardMock mClipboardMock;

	private void makeEncryptable() {
		mMainActivityMock.mTestHasMessage = true;
		mMainActivityMock.mTestHasPassword = true;
		mMainActivityMock.mTestIsBusy = false;
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		mMainActivityMock = new MainActivityMock();
		mJsEncryptorMock = new JsEncryptorMock();
		mClipboardMock = new ClipboardMock();

		mEncrypt = new Encrypt(mMainActivityMock, mJsEncryptorMock, mClipboardMock);
	}

	public void testEncryptAndUpdate_copiesEncryptedMessageToClipboard() {
		makeEncryptable();

		mEncrypt.encryptAndUpdate();
		mJsEncryptorMock.mTestEncryptCallback.onResult("Encrypted test message :)");
		assertEquals("Encrypted test message :)", mClipboardMock.mTestClipboardContent);
		assertTrue(mEncrypt.getJustCopied());
	}

	public void testEncryptAndUpdate_doNotEncryptIfNotEncryptable() {
		mMainActivityMock.mTestHasMessage = false;
		mMainActivityMock.mTestHasPassword = true;
		mMainActivityMock.mTestIsBusy = false;

		mMainActivityMock.mTestTrimmedPassword = "test password one two";

		mEncrypt.encryptAndUpdate();

		assertNull(mJsEncryptorMock.mTestEncryptPassword);
	}

	public void testEncryptAndUpdate_encryptsMessageWithPassword() {
		makeEncryptable();

		mMainActivityMock.mTestTrimmedMessage = "message to encrypt";
		mMainActivityMock.mTestTrimmedPassword = "test password one two";
		mEncrypt.encryptAndUpdate();
		assertEquals("message to encrypt", mJsEncryptorMock.mTestEncryptText);
		assertEquals("test password one two", mJsEncryptorMock.mTestEncryptPassword);
	}

	public void testEncryptAndUpdate_showsEncryptedMessage() {
		makeEncryptable();

		mEncrypt.encryptAndUpdate();
		mJsEncryptorMock.mTestEncryptCallback.onResult("Encrypted test message :)");
		assertEquals("Encrypted test message :)", mMainActivityMock.mTestMessage);
	}

	public void testEncryptAndUpdate_updatedEncryptButtonTittle() {
		makeEncryptable();

		mEncrypt.encryptAndUpdate();
		mJsEncryptorMock.mTestEncryptCallback.onResult("Encrypted test message :)");
		assertTrue(mMainActivityMock.mEncryptButtonTitleUpdated);
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