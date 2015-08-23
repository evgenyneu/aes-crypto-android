package com.evgenii.aescrypto.mocks;

import com.evgenii.aescrypto.interfaces.ClipboardInterface;

public class ClipboardMock implements ClipboardInterface {
	public String mTestClipboardContent;

	@Override
	public String get() {
		return mTestClipboardContent;
	}

	@Override
	public void set(String text) {
		mTestClipboardContent = text;
	}

}
