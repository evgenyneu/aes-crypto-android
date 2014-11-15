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
	private String mMenuTitle;

	public Decrypt(MainActivityInterface activity, JsEncryptorInterface jsEncryptor,
			ClipboardInterface clipboard) {
		mActivity = activity;
		mJsEncryptor = jsEncryptor;
		mClipboard = clipboard;
	}

	public void decryptAndUpdate() {
		mActivity.updateBusy(true);
		mJsEncryptor.decrypt(mActivity.trimmedMessage(), mActivity.trimmedPassword(),
				new JsCallback() {
					@Override
					public void onResult(final String decryptedTextFromJs) {
						mActivity.updateBusy(false);
						mActivity.setMessage(decryptedTextFromJs);
						setDecryptedText(decryptedTextFromJs);
						updateDecryptButton();
					}
				});
	}

	public String getDecryptedText() {
		return mDecryptedText;
	}

	public String getMenuTitle() {
		return mMenuTitle;
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

	public void setDecryptedText(String decryptedText) {
		mDecryptedText = decryptedText;
		if (mDecryptedText == null)
			return;

		mDecryptedText = mDecryptedText.trim();

		if (mDecryptedText.length() == 0) {
			mDecryptedText = null;
		}
	}

	public void setTextToDecrypt(String textToDecrypt) {
		mTextToDecrypt = textToDecrypt;
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

	public void updateDecryptButtonTitle(String title) {
		if (title == null)
			return;

		title = title.replaceAll("\\r|\\n", " ");

		if (title.length() > 10) {
			title = title.substring(0, 10);
			title = title + "...";
		}

		mMenuTitle = "↓ " + title;
	}
}
