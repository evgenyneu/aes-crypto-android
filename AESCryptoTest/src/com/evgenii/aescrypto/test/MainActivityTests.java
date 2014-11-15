package com.evgenii.aescrypto.test;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

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

	public void testFillPasswordAndMessage_clickEncrypt_encryptsText() throws InterruptedException {

		fillIn(R.id.password, "Test Password");
		fillIn(R.id.message, "Test Tech Bubble");

		TouchUtils.clickView(this, mActivity.findViewById(R.id.encryptButton));

		final TextView messageTextView = (TextView) mActivity.findViewById(R.id.message);

		Log.d("ii", "!!!!!!!!!!!!!!" + messageTextView.getText().toString() + "!!!!!");

		for (int i = 0; i < 100; i++) {
			Thread.sleep(100);
			if (!messageTextView.getText().toString().equals("Test Tech Bubble")) {
				break;
			}
		}

		assertEquals("AESCryptoV10", messageTextView.getText().toString().substring(0, 12));
	}

	public void testFillPasswordAndMessage_clickEncrypt_putEncryptedMessageToClipboard()
			throws InterruptedException {

		// fillIn(R.id.password, "Test Password");
		// fillIn(R.id.message, "Test Tech Bubble");
		//
		// TouchUtils.clickView(this,
		// mActivity.findViewById(R.id.action_encrypt));
		//
		// final ClipboardManager clipboard = (ClipboardManager) mActivity
		// .getSystemService(Context.CLIPBOARD_SERVICE);
		//
		// for (int i = 0; i < 100; i++) {
		// if (clipboard.hasPrimaryClip()) {
		// break;
		// }
		// Thread.sleep(100);
		// }
		//
		// final ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
		// final String clipboardText = (String) item.getText();
		// assertEquals("AESCryptoV10", clipboardText.substring(0, 12));
	}

	public void testFillPasswordAndMessage_resumesActivityWithEncryptedMessageInClipboard_showsDecryptedMessage()
			throws InterruptedException {
		// final ClipboardManager clipboard = (ClipboardManager) mActivity
		// .getSystemService(Context.CLIPBOARD_SERVICE);
		// final ClipData clip = ClipData
		// .newPlainText("Message ecrypted by AESCrypto",
		// "AESCryptoV108f46e2fb15f50e9c170442ec5ec70e6fcded6378b13f1a659f0eb65e8eddb2335de8e76be90b2f0a");
		// clipboard.setPrimaryClip(clip);
		//
		// getInstrumentation().runOnMainSync(new Runnable() {
		// @Override
		// public void run() {
		// mActivity.onResume();
		// }
		// });
		//
		// fillIn(R.id.password, "test");
		//
		// final String expectedDecryptBtnText = "↓ My Test Me...";
		// MenuItem menuItem = null;
		// for (int i = 0; i < 100; i++) {
		// menuItem = mActivity.mMenuForTest.findItem(R.id.action_decrypt);
		// if (menuItem != null && menuItem.getTitle() != null
		// && menuItem.getTitle().toString().equals(expectedDecryptBtnText)) {
		// break;
		// }
		// Thread.sleep(100);
		// }
		//
		// assertEquals(expectedDecryptBtnText, menuItem.getTitle());
		//
		// final View decryptMenuItem =
		// mActivity.findViewById(R.id.action_decrypt);
		// TouchUtils.clickView(this, decryptMenuItem);
		//
		// final String expectedMessage = "My Test Message 日本";
		// final EditText messageEditText = (EditText)
		// mActivity.findViewById(R.id.message);
		//
		// for (int i = 0; i < 100; i++) {
		// if (messageEditText.getText().toString().equals(expectedMessage)) {
		// break;
		// }
		// Thread.sleep(100);
		// }
		//
		// assertEquals(expectedMessage, messageEditText.getText().toString());
	}
}
