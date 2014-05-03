package com.evgenii.aescrypto;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends Activity {
	private JsEncryptor mJsEncryptor;
	private EditText mMessage;
	private EditText mPassword;
	private boolean mIsBusy;
	private Clipboard mClipboard;
	private Encrypt mEncrypt;
	private Decrypt mDecrypt;

	public JsEncryptor getEncryptor() {
		return mJsEncryptor;
	}

	public boolean hasMessage() {
		return trimmedMessage().length() > 0;
	}

	public boolean hasPassword() {
		return trimmedPassword().length() > 0;
	}

	public boolean isBusy() {
		return mIsBusy;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		mJsEncryptor = JsEncryptor.evaluateAllScripts(this);

		mMessage = (EditText) findViewById(R.id.message);
		mPassword = (EditText) findViewById(R.id.password);
		mClipboard = new Clipboard(this);
		mDecrypt = new Decrypt(this, mJsEncryptor, mClipboard);
		mEncrypt = new Encrypt(this, mJsEncryptor, mClipboard);

		setupInputChange();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_encrypt:
			mEncrypt.encryptAndUpdate();
			return true;
		case R.id.action_decrypt:
			mDecrypt.showFullDecryptedText();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void onPasswordChanged() {
		mDecrypt.decryptAndUpdate();
	}

	private void onPasswordOrMessageChanged() {
		mEncrypt.updateJustCopied(false);
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		menu.findItem(R.id.action_decrypt).setVisible(mDecrypt.isDecryptable());
		menu.findItem(R.id.action_decrypt).setTitle(mDecrypt.getMenuTitle());

		menu.findItem(R.id.action_encrypt).setEnabled(mEncrypt.isEncryptable());
		menu.findItem(R.id.action_encrypt).setTitle(mEncrypt.getMenuTitle());

		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	protected void onResume() {
		super.onResume();
		mDecrypt.storeTextToDecrypt();
		mDecrypt.decryptAndUpdate();
	}

	public void setMessage(String message) {
		mMessage.setText(message);
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
				onPasswordChanged();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
		});
	}

	public String trimmedMessage() {
		return mMessage.getText().toString().trim();
	}

	public String trimmedPassword() {
		return mPassword.getText().toString().trim();
	}

	public void updateBusy(boolean isBusy) {
		mIsBusy = isBusy;
		invalidateOptionsMenu();
	}

}
