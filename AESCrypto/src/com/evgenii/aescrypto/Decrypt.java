package com.evgenii.aescrypto;

import com.evgenii.jsevaluator.interfaces.JsCallback;

public class Decrypt {
	protected MainActivity mActivity;
	protected JsEncryptor mJsEncryptor;
	protected String mTextToDecrypt;
	protected String mDecryptedText;
	public String currentDecryptMenuTitle;

	public Decrypt(MainActivity activity) {
		mActivity = activity;
		mJsEncryptor = activity.mJsEncryptor;
	}

	public void decryptAndUpdate() {
		if (mTextToDecrypt == null)
			return;

		mActivity.isEncryptingOrDecrypting = true;
		mJsEncryptor.decrypt(mTextToDecrypt, mActivity.passwordTrimmed(), new JsCallback() {
			@Override
			public void onResult(final String decryptedTextFromJs) {
				mActivity.isEncryptingOrDecrypting = false;
				finishedDecrypting(decryptedTextFromJs);
			}
		});
	}

	protected void finishedDecrypting(String decryptedText) {
		mDecryptedText = decryptedText;
		if (mDecryptedText != null) {
			mDecryptedText = mDecryptedText.trim();
			if (mDecryptedText.length() == 0) {
				mDecryptedText = null;
			}
		}

		updateDecryptButton();
	}

	public boolean isDecryptable() {
		if (mActivity.isEncryptingOrDecrypting)
			return false;

		if (mDecryptedText == null || mDecryptedText.equals(mActivity.messageTrimmed()))
			return false;

		return true;
	}

	public void storeTextToDecrypt() {
		final String clipboardText = mActivity.mClipboard.get();
		if (!mJsEncryptor.isEncrypted(clipboardText))
			return;

		mTextToDecrypt = clipboardText;
	}

	protected void updateDecryptButton() {
		updateDecryptButtonTitle(mDecryptedText);
		mActivity.invalidateOptionsMenu();
	}

	protected void updateDecryptButtonTitle(String title) {
		if (title == null)
			return;

		if (title.length() > 10) {
			title = title.substring(0, 10);
			title = title + "...";
		}

		currentDecryptMenuTitle = "↓" + title;
	}
}
