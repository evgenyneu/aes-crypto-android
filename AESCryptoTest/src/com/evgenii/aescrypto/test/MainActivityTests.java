package com.evgenii.aescrypto.test;

import java.util.ArrayList;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.EditText;

import com.evgenii.aescrypto.JsEncryptor;
import com.evgenii.aescrypto.MainActivity;
import com.evgenii.aescrypto.R;
import com.evgenii.jsevaluator.interfaces.JsCallback;

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

	public void testDecrypt() throws InterruptedException {
		final JsEncryptor encryptor = mActivity.getEncryptor();
		final ArrayList<String> myResult = new ArrayList<String>();

		getInstrumentation().runOnMainSync(new Runnable() {
			@Override
			public void run() {
				encryptor
						.decrypt(
								"AESCryptoV108f46e2fb15f50e9c170442ec5ec70e6fcded6378b13f1a659f0eb65e8eddb2335de8e76be90b2f0a",
								"test", new JsCallback() {
									@Override
									public void onResult(String result) {
										myResult.add(result);
									}
								});
			}
		});

		for (int i = 0; i < 100; i++) {
			Thread.sleep(50);
			if (myResult.size() == 1) {
				break;
			}
		}

		assertEquals(1, myResult.size());
		assertEquals("My Test Message 日本", myResult.get(0));
	}

	public void testEncrypt() throws InterruptedException {
		final JsEncryptor encryptor = mActivity.getEncryptor();
		final ArrayList<String> myResult = new ArrayList<String>();

		getInstrumentation().runOnMainSync(new Runnable() {
			@Override
			public void run() {
				encryptor.encrypt("hello", "world", new JsCallback() {
					@Override
					public void onResult(String result) {
						myResult.add(result);
					}
				});
			}
		});

		for (int i = 0; i < 100; i++) {
			Thread.sleep(50);
			if (myResult.size() == 1) {
				break;
			}
		}

		assertEquals(1, myResult.size());
		assertEquals("AESCryptoV10", myResult.get(0).substring(0, 12));
	}

	public void testEncryptAndDecrypt() throws InterruptedException {
		final JsEncryptor encryptor = mActivity.getEncryptor();
		final ArrayList<String> myResult = new ArrayList<String>();

		getInstrumentation().runOnMainSync(new Runnable() {
			@Override
			public void run() {
				encryptor.encrypt("Hello there", "my password", new JsCallback() {
					@Override
					public void onResult(String resultEncrypt) {
						encryptor.decrypt(resultEncrypt, "my password", new JsCallback() {
							@Override
							public void onResult(String resultDecrypt) {
								myResult.add(resultDecrypt);
							}
						});
					}
				});
			}
		});

		for (int i = 0; i < 100; i++) {
			Thread.sleep(50);
			if (myResult.size() == 1) {
				break;
			}
		}

		assertEquals(1, myResult.size());
		assertEquals("Hello there", myResult.get(0));
	}

	public void testFillPasswordAndMessage_clickEncrypt_shareEncryptedMessage()
			throws InterruptedException {
		fillIn(R.id.password, "Test Password");
		fillIn(R.id.message, "Test Tech Bubble");

		TouchUtils.clickView(this, mActivity.findViewById(R.id.action_encrypt));

		for (int i = 0; i < 100; i++) {
			if (mActivity.mSharingIntent != null) {
				break;
			}
			Thread.sleep(100);
		}

		assertEquals("android.intent.action.SEND", mActivity.mSharingIntent.getAction());
		assertEquals("text/plain", mActivity.mSharingIntent.getType());

		final String encryptedText = mActivity.mSharingIntent.getStringExtra(Intent.EXTRA_TEXT);
		assertEquals("AESCryptoV10", encryptedText.substring(0, 12));
	}
}