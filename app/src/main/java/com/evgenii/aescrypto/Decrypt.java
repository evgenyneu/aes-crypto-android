package com.evgenii.aescrypto;

import com.evgenii.aescrypto.interfaces.JsEncryptorInterface;
import com.evgenii.aescrypto.interfaces.MainActivityInterface;
import com.evgenii.jsevaluator.interfaces.JsCallback;

public class Decrypt {
	private final MainActivityInterface mActivity;
	private final JsEncryptorInterface mJsEncryptor;

	public Decrypt(MainActivityInterface activity, JsEncryptorInterface jsEncryptor) {
		mActivity = activity;
		mJsEncryptor = jsEncryptor;
	}

	public void decryptAndUpdate() {
		if (!isDecryptable())
			return;

		mActivity.updateBusy(true);
		mJsEncryptor.decrypt(mActivity.trimmedMessage(), mActivity.trimmedPassword(),
				new JsCallback() {
					@Override
					public void onResult(final String decryptedTextFromJs) {
						mActivity.updateBusy(false);

						if (decryptedTextFromJs != null && !decryptedTextFromJs.trim().isEmpty()) {
							mActivity.setMessage(decryptedTextFromJs);
						}
					}

					@Override
					public void onError(String errorMessage) {
						// Process JavaScript error here.
						// This method is called in the UI thread.
					}
				});
	}

	public boolean isDecryptable() {
		if (mActivity.isBusy())
			return false;

		if (!mActivity.hasPassword())
			return false;

		if (!mJsEncryptor.isEncrypted(mActivity.trimmedMessage()))
			return false;

		return true;
	}
}
