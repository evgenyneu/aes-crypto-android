package com.evgenii.aescrypto;

import com.evgenii.aescrypto.interfaces.MainActivityInterface;
import com.evgenii.jsevaluator.interfaces.JsCallback;

public class Encrypt {
	private final MainActivityInterface mActivity;
	private final JsEncryptor mJsEncryptor;
	private final Clipboard mClipboard;
	private boolean mJustCopied;

	public Encrypt(MainActivityInterface activity, JsEncryptor jsEncryptor, Clipboard clipboard) {
		mActivity = activity;
		mJsEncryptor = jsEncryptor;
		mClipboard = clipboard;
	}

	public void encryptAndUpdate() {
		mActivity.updateBusy(true);
		mJsEncryptor.encrypt(mActivity.trimmedMessage(), mActivity.trimmedPassword(),
				new JsCallback() {
					@Override
					public void onResult(final String encryptedMessage) {
						mActivity.updateBusy(false);
						storeMessageInClipboard(encryptedMessage);
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
		mActivity.invalidateOptionsMenu();
	}

}
