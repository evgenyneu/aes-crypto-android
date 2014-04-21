package com.evgenii.aescrypto.test;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.EditText;

import com.evgenii.aescrypto.MainActivity;
import com.evgenii.aescrypto.R;

public class MainActivityTests extends ActivityInstrumentationTestCase2<MainActivity> {
	private MainActivity mActivity;

	public MainActivityTests() {
		super(MainActivity.class);
	}

	private void fillIn(int id, String text) {
		final EditText passwordEditText = (EditText) mActivity.findViewById(id);

		// Send string input value
		getInstrumentation().runOnMainSync(new Runnable() {
			@Override
			public void run() {
				passwordEditText.requestFocus();
			}
		});
		getInstrumentation().waitForIdleSync();
		getInstrumentation().sendStringSync(text);
		getInstrumentation().waitForIdleSync();
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mActivity = getActivity();
	}

	public void testFillPasswordAndMessage_clickEncrypt_shareEncryptedMessage()
			throws InterruptedException {
		fillIn(R.id.password, "Test Password");
		fillIn(R.id.message, "Test Tech Bubble");

		TouchUtils.clickView(this, mActivity.findViewById(R.id.action_encrypt));

		final ClipboardManager clipboard = (ClipboardManager) mActivity
				.getSystemService(Context.CLIPBOARD_SERVICE);

		for (int i = 0; i < 100; i++) {
			if (clipboard.hasPrimaryClip()) {
				break;
			}
			Thread.sleep(100);
		}

		final ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
		final String clipboardText = (String) item.getText();
		assertEquals("AESCryptoV10", clipboardText.substring(0, 12));
	}
}
