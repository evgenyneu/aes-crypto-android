package com.evgenii.aescrypto;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.evgenii.jsevaluator.interfaces.JsCallback;

public class MainActivity extends Activity {

	public String currentDecryptMenuTitle;
	protected JsEncryptor mJsEncryptor;
	protected EditText mMessage;
	protected EditText mPassword;
	protected boolean isEncrypting = false;
	protected boolean mJustCopied = false;

	public String getEncryptMenuTitle() {
		if (mJustCopied)
			return getResources().getString(R.string.menu_encrypt_title_copied);
		else
			return getResources().getString(R.string.menu_encrypt_title);
	}

	public JsEncryptor getEncryptor() {
		return mJsEncryptor;
	}

	public boolean hasMessage() {
		return mMessage.getText().toString().trim().length() > 0;
	}

	public boolean hasPassword() {
		return mPassword.getText().toString().trim().length() > 0;
	}

	public boolean isEncryptable() {
		return hasMessage() && hasPassword() && !isEncrypting;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		currentDecryptMenuTitle = getResources().getString(R.string.menu_decrypt_title);
		setContentView(R.layout.activity_main);

		mJsEncryptor = JsEncryptor.evaluateAllScripts(this);

		mMessage = (EditText) findViewById(R.id.message);
		mPassword = (EditText) findViewById(R.id.password);

		setupInputChange();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onEncryptClicked() {
		final String message = mMessage.getText().toString();
		final String password = mPassword.getText().toString();
		isEncrypting = true;
		invalidateOptionsMenu();
		mJsEncryptor.encrypt(message, password, new JsCallback() {
			@Override
			public void onResult(final String encryptedMessage) {
				isEncrypting = false;
				invalidateOptionsMenu();
				shareMessage(encryptedMessage);
			}
		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.action_encrypt:
			onEncryptClicked();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void onPasswordOrMessageChanged() {
		mJustCopied = false;
		invalidateOptionsMenu();
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		menu.findItem(R.id.action_decrypt).setTitle(currentDecryptMenuTitle);
		menu.findItem(R.id.action_encrypt).setTitle(getEncryptMenuTitle());

		menu.findItem(R.id.action_encrypt).setEnabled(isEncryptable());
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	protected void onResume() {
		super.onResume();
		// TODO: Read text from clipboard
	}

	private void setupInputChange() {
		mMessage.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
				onPasswordOrMessageChanged();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
		});

		mPassword.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
				onPasswordOrMessageChanged();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
		});
	}

	private void shareMessage(String message) {
		final ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
		final ClipData clip = ClipData.newPlainText("Message ecrypted by AESCrypto", message);
		clipboard.setPrimaryClip(clip);

		mJustCopied = true;
		invalidateOptionsMenu();
	}
}
