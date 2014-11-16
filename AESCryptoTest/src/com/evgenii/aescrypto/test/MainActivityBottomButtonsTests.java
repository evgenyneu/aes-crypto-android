package com.evgenii.aescrypto.test;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.evgenii.aescrypto.MainActivity;
import com.evgenii.aescrypto.R;

public class MainActivityBottomButtonsTests extends ActivityInstrumentationTestCase2<MainActivity> {
	private MainActivity mActivity;

	public MainActivityBottomButtonsTests() {
		super(MainActivity.class);
	}

	private void fillIn(int id, String text) throws InterruptedException {
		final EditText editText = (EditText) mActivity.findViewById(id);

		// Send string input value
		getInstrumentation().runOnMainSync(new Runnable() {
			@Override
			public void run() {
				editText.requestFocus();
			}
		});
		getInstrumentation().waitForIdleSync();
		getInstrumentation().sendStringSync(text);
		getInstrumentation().waitForIdleSync();

		Thread.sleep(100);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mActivity = getActivity();
	}

	public void testTapCopyButton_doNotPutEmptyMessageToClipboard() throws InterruptedException {
		fillIn(R.id.message, " ");

		final ClipboardManager clipboard = (ClipboardManager) mActivity
				.getSystemService(Context.CLIPBOARD_SERVICE);

		final ClipData clip = ClipData.newPlainText("Message ecrypted by test",
				"Previous message in clipboard");
		clipboard.setPrimaryClip(clip);

		TouchUtils.clickView(this, mActivity.findViewById(R.id.copyButton));

		final ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
		final String clipboardText = (String) item.getText();
		assertEquals("Previous message in clipboard", clipboardText);
	}

	public void testTapCopyButton_putsMessageToClipboard() throws InterruptedException {
		fillIn(R.id.message, "Possum Hype Check");

		TouchUtils.clickView(this, mActivity.findViewById(R.id.copyButton));

		final ClipboardManager clipboard = (ClipboardManager) mActivity
				.getSystemService(Context.CLIPBOARD_SERVICE);

		final ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
		final String clipboardText = (String) item.getText();
		assertEquals("Possum Hype Check", clipboardText);
	}

	public void testTapPasteButton_showsMessageFromClipboard() throws InterruptedException {
		final ClipboardManager clipboard = (ClipboardManager) mActivity
				.getSystemService(Context.CLIPBOARD_SERVICE);

		final ClipData clip = ClipData.newPlainText("Message ecrypted by test",
				"Interstellar movie was dope!");
		clipboard.setPrimaryClip(clip);

		TouchUtils.clickView(this, mActivity.findViewById(R.id.pasteButton));

		final TextView messageTextView = (TextView) mActivity.findViewById(R.id.message);
		assertEquals("Interstellar movie was dope!", messageTextView.getText().toString());
	}
}