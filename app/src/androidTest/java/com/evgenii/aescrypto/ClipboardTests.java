package com.evgenii.aescrypto;

import android.test.AndroidTestCase;

import com.evgenii.aescrypto.Clipboard;

public class ClipboardTests extends AndroidTestCase {
	protected Clipboard mClipboard;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mClipboard = new Clipboard(mContext);
	}

	public void testStoreTextToClipboard() {
		mClipboard.set("hello world");
		assertEquals("hello world", mClipboard.get());
	}
}
