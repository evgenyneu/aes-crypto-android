package com.evgenii.eascrypto.test.mocks;

import com.evgenii.aescrypto.interfaces.ClipboardInterface;

public class ClipboardMock implements ClipboardInterface {
	private String mTestClipboardContent;

	@Override
	public String get() {
		return mTestClipboardContent;
	}

	@Override
	public void set(String text) {
		mTestClipboardContent = text;
	}

}
