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

	public void testDecryptAndUpdate_decryptsTextWithThePassword() {
		mMainActivityMock.mTestTrimmedPassword = "test password";
		mMainActivityMock.mTestTrimmedMessage = "test text to decrypt";

		mDecrypt.decryptAndUpdate();

		assertEquals("test text to decrypt", mJsEncryptorMock.mTestDecryptText);
		assertEquals("test password", mJsEncryptorMock.mTestDecryptPassword);

	}

	public void testDecryptAndUpdate_receiveDecryptedText() {
		mDecrypt.setTextToDecrypt("test text to decrypt");
		mDecrypt.decryptAndUpdate();

		mJsEncryptorMock.mTestDecryptCallback.onResult("test decrypted string");
		assertEquals("test decrypted string", mDecrypt.getDecryptedText());
		assertEquals("↓ test decry...", mDecrypt.getMenuTitle());
		assertTrue(mMainActivityMock.mTestOptionsMenuInvalidated);
	}

	public void testDecryptAndUpdate_showsDecryptedText() {
		mDecrypt.decryptAndUpdate();

		mJsEncryptorMock.mTestDecryptCallback.onResult("test decrypted string");
		assertEquals("test decrypted string", mMainActivityMock.mTestMessage);
	}

	public void testGetTextToDecrypt() {
		mClipboardMock.set("AESCryptoV10 ˁ˚ᴥ˚ˀ");
		mJsEncryptorMock.mTestIsEcrypted = true;
		mDecrypt.storeTextToDecrypt();
		assertEquals("AESCryptoV10 ˁ˚ᴥ˚ˀ", mDecrypt.getTextToDecrypt());
	}

	public void testIsDecryptable_NO_whenBusy() {
		mMainActivityMock.mTestIsBusy = true;
		mMainActivityMock.mTestTrimmedMessage = null;
		mDecrypt.setDecryptedText("Hello");
		assertFalse(mDecrypt.isDecryptable());
	}

	public void testIsDecryptable_NO_whenTextEqualsMessage() {
		mMainActivityMock.mTestIsBusy = false;
		mMainActivityMock.mTestTrimmedMessage = "Hello";
		mDecrypt.setDecryptedText("Hello");
		assertFalse(mDecrypt.isDecryptable());
	}

	public void testIsDecryptable_NO_withNoText() {
		mMainActivityMock.mTestIsBusy = false;
		mMainActivityMock.mTestTrimmedMessage = null;
		mDecrypt.setDecryptedText(null);
		assertFalse(mDecrypt.isDecryptable());
	}

	public void testIsDecryptable_YES_withTextToDecrypt() {
		mMainActivityMock.mTestIsBusy = false;
		mMainActivityMock.mTestTrimmedMessage = null;
		mDecrypt.setDecryptedText("Hello");
		assertTrue(mDecrypt.isDecryptable());
	}

	public void testSetDecryptedText() {
		mDecrypt.setDecryptedText("Hi there");
		assertEquals("Hi there", mDecrypt.getDecryptedText());
	}

	public void testSetDecryptedText_empty() {
		mDecrypt.setDecryptedText("  ");
		assertEquals(null, mDecrypt.getDecryptedText());
	}

	public void testSetDecryptedText_null() {
		mDecrypt.setDecryptedText(null);
		assertEquals(null, mDecrypt.getDecryptedText());
	}

	public void testShowFullDecryptedText() {
		mDecrypt.setDecryptedText("One two");
		mDecrypt.showFullDecryptedText();
		assertEquals("One two", mMainActivityMock.mTestMessage);
		assertTrue(mMainActivityMock.mTestOptionsMenuInvalidated);
	}

	public void testUpdateDecryptButtonTitle() {
		mDecrypt.updateDecryptButtonTitle("1234567890");
		assertEquals("↓ 1234567890", mDecrypt.getMenuTitle());
	}

	public void testUpdateDecryptButtonTitle_removeNewLines() {
		mDecrypt.updateDecryptButtonTitle("one\ntwo");
		assertEquals("↓ one two", mDecrypt.getMenuTitle());
	}

	public void testUpdateDecryptButtonTitle_truncateLongText() {
		mDecrypt.updateDecryptButtonTitle("1234567890this is a long message");
		assertEquals("↓ 1234567890...", mDecrypt.getMenuTitle());
	}
}
