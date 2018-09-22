package com.evgenii.aescrypto;

import com.evgenii.aescrypto.interfaces.ClipboardInterface;
import com.evgenii.aescrypto.interfaces.JsEncryptorInterface;
import com.evgenii.aescrypto.interfaces.MainActivityInterface;
import com.evgenii.jsevaluator.interfaces.JsCallback;

public class Encrypt {
	private final MainActivityInterface mActivity;
	private final JsEncryptorInterface mJsEncryptor;
	private final ClipboardInterface mClipboard;
	private boolean mJustCopied;

	public Encrypt(MainActivityInterface activity, JsEncryptorInterface jsEncryptor,
			ClipboardInterface clipboard) {
		mActivity = activity;
		mJsEncryptor = jsEncryptor;
		mClipboard = clipboard;
	}

	public void encryptAndUpdate() {
		if (!isEncryptable())
			return;

		mActivity.updateBusy(true);
		mJsEncryptor.encrypt(mActivity.trimmedMessage(), mActivity.trimmedPassword(),
				new JsCallback() {
					@Override
					public void onResult(final String encryptedMessage) {
						storeMessageInClipboard(encryptedMessage);
						mActivity.setMessage(encryptedMessage);
						mActivity.updateEncryptButtonTitle();
						mActivity.updateBusy(false);
					}

					@Override
					public void onError(String errorMessage) {
						// Process JavaScript error here.
						// This method is called in the UI thread.
					}
				});
	}

	public boolean getJustCopied() {
		return mJustCopied;
	}

	public boolean isEncryptable() {
		return mActivity.hasMessage() && mActivity.hasPassword() && !mActivity.isBusy();
	}

	private void storeMessageInClipboard(String message) {
		mClipboard.set(message);
		updateJustCopied(true);
	}

	public void updateJustCopied(boolean justCopied) {
		mJustCopied = justCopied;
	}

}
