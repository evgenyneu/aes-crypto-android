package com.evgenii.aescrypto;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;

import com.evgenii.aescrypto.interfaces.ClipboardInterface;

public class Clipboard implements ClipboardInterface {
	protected Context mContext;

	public Clipboard(Context context) {
		mContext = context;
	}

	@Override
	public String get() {
		final ClipboardManager clipboard = (ClipboardManager) mContext
				.getSystemService(Activity.CLIPBOARD_SERVICE);
		if (!clipboard.hasPrimaryClip())
			return null;

		if (clipboard.getPrimaryClipDescription().getMimeTypeCount() == 0)
			return null;

		final String mimeType = clipboard.getPrimaryClipDescription().getMimeType(0);

		if (!mimeType.equals(ClipDescription.MIMETYPE_TEXT_HTML)
				&& !mimeType.equals(ClipDescription.MIMETYPE_TEXT_PLAIN))
			return null;

		final ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);

		final CharSequence clipboardData = item.getText();
		if (clipboardData == null)
			return null;

		return clipboardData.toString();
	}

	@Override
	public void set(String text) {
		final ClipboardManager clipboard = (ClipboardManager) mContext
				.getSystemService(Activity.CLIPBOARD_SERVICE);
		final ClipData clip = ClipData.newPlainText("Message ecrypted by AESCrypto", text);
		clipboard.setPrimaryClip(clip);
	}
}
