package com.evgenii.aescrypto;

import com.evgenii.aescrypto.interfaces.ClipboardInterface;
import com.evgenii.aescrypto.interfaces.JsEncryptorInterface;
import com.evgenii.aescrypto.interfaces.MainActivityInterface;
import com.evgenii.jsevaluator.interfaces.JsCallback;

public class Decrypt {
	private final MainActivityInterface mActivity;
	private final JsEncryptorInterface mJsEncryptor;
	private final ClipboardInterface mClipboard;
	private String mTextToDecrypt;
	private String mDecryptedText;
	private String mCurrentDecryptMenuTitle;

	public Decrypt(MainActivityInterface activity, JsEncryptorInterface jsEncryptor,
			ClipboardInterface clipboard) {
		mActivity = activity;
		mJsEncryptor = jsEncryptor;
		mClipboard = clipboard;
	}

	public void decryptAndUpdate() {
		if (mTextToDecrypt == null)
			return;

		mActivity.updateBusy(true);
		mJsEncryptor.decrypt(mTextToDecrypt, mActivity.trimmedPassword(), new JsCallback() {
			@Override
			public void onResult(final String decryptedTextFromJs) {
				mActivity.updateBusy(false);
				finishedDecrypting(decryptedTextFromJs);
			}
		});
	}

	private void finishedDecrypting(String decryptedText) {
		mDecryptedText = decryptedText;
		if (mDecryptedText != null) {
			mDecryptedText = mDecryptedText.trim();
			if (mDecryptedText.length() == 0) {
				mDecryptedText = null;
			}
		}

		updateDecryptButton();
	}

	public String getMenuTitle() {
		return mCurrentDecryptMenuTitle;
	}

	public String getTextToDecrypt() {
		return mTextToDecrypt;
	}

	public boolean isDecryptable() {
		if (mActivity.isBusy())
			return false;

		if (mDecryptedText == null || mDecryptedText.equals(mActivity.trimmedMessage()))
			return false;

		return true;
	}

	public void showFullDecryptedText() {
		if (mDecryptedText == null)
			return;

		mActivity.setMessage(mDecryptedText);
		mActivity.invalidateOptionsMenu();
	}

	public void storeTextToDecrypt() {
		final String clipboardText = mClipboard.get();
		if (!mJsEncryptor.isEncrypted(clipboardText))
			return;

		mTextToDecrypt = clipboardText;
	}

	private void updateDecryptButton() {
		updateDecryptButtonTitle(mDecryptedText);
		mActivity.invalidateOptionsMenu();
	}

	private void updateDecryptButtonTitle(String title) {
		if (title == null)
			return;

		title = title.replaceAll("\\r|\\n", " ");

		if (title.length() > 10) {
			title = title.substring(0, 10);
			title = title + "...";
		}

		mCurrentDecryptMenuTitle = "↓" + title;
	}
}
