package com.evgenii.aescrypto;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.evgenii.aescrypto.interfaces.MainActivityInterface;

public class MainActivity extends Activity implements MainActivityInterface {
	private JsEncryptor mJsEncryptor;
	private EditText mMessage;
	private EditText mPassword;
	private boolean mIsBusy;
	private Clipboard mClipboard;
	private Encrypt mEncrypt;
	private Decrypt mDecrypt;

	// public Menu mMenuForTest;

	private String getEncryptButtonTitle() {
		if (mEncrypt.getJustCopied())
			return getResources().getString(R.string.menu_encrypt_title_copied);
		else
			return getResources().getString(R.string.menu_encrypt_title);
	}

	public JsEncryptor getEncryptor() {
		return mJsEncryptor;
	}

	@Override
	public boolean hasMessage() {
		return trimmedMessage().length() > 0;
	}

	@Override
	public boolean hasPassword() {
		return trimmedPassword().length() > 0;
	}

	@Override
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
		mDecrypt = new Decrypt(this, mJsEncryptor);
		mEncrypt = new Encrypt(this, mJsEncryptor, mClipboard);

		setupInputChange();

		setupActionBar();
	}

	public void onDecryptTapped(View view) {
		mDecrypt.decryptAndUpdate();
	}

	public void onEncryptTapped(View view) {
		mEncrypt.encryptAndUpdate();
	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// mMenuForTest = menu;
	// getMenuInflater().inflate(R.menu.main, menu);
	// return true;
	// }
	// @Override
	// public boolean onOptionsItemSelected(MenuItem item) {
	// switch (item.getItemId()) {
	// case R.id.action_encrypt:
	// mEncrypt.encryptAndUpdate();
	// return true;
	// case R.id.action_decrypt:
	// mDecrypt.showFullDecryptedText();
	// return true;
	// default:
	// return super.onOptionsItemSelected(item);
	// }
	// }

	// private void onPasswordChanged() {
	// mDecrypt.decryptAndUpdate();
	// }

	private void onPasswordOrMessageChanged() {
		if (isBusy())
			return;

		mEncrypt.updateJustCopied(false);
		updateEncryptButtonTitle();
	}

	// @Override
	// public boolean onPrepareOptionsMenu(Menu menu) {
	// menu.findItem(R.id.action_decrypt).setVisible(mDecrypt.isDecryptable());
	// menu.findItem(R.id.action_decrypt).setTitle(mDecrypt.getMenuTitle());
	//
	// menu.findItem(R.id.action_encrypt).setEnabled(mEncrypt.isEncryptable());
	// menu.findItem(R.id.action_encrypt).setTitle(getEncryptMenuTitle());
	//
	// return super.onPrepareOptionsMenu(menu);
	// }
	// @Override
	// public void onResume() {
	// super.onResume();
	// mDecrypt.storeTextToDecrypt();
	// mDecrypt.decryptAndUpdate();
	// }

	public void onShowHelpClicked(View view) {
		final Intent intent = new Intent(this, HelpActivity.class);
		startActivity(intent);
	}

	@Override
	public void setMessage(String message) {
		mMessage.setText(message);
	}

	protected void setupActionBar() {
		final ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayUseLogoEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayShowCustomEnabled(true);
		final View actionBarView = getLayoutInflater().inflate(R.layout.main_action_bar, null);
		actionBar.setCustomView(actionBarView);
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
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

	@Override
	public String trimmedMessage() {
		return mMessage.getText().toString().trim();
	}

	@Override
	public String trimmedPassword() {
		return mPassword.getText().toString().trim();
	}

	@Override
	public void updateBusy(boolean isBusy) {
		mIsBusy = isBusy;
	}

	@Override
	public void updateEncryptButtonTitle() {
		final Button encryptButton = (Button) findViewById(R.id.encryptButton);
		encryptButton.setText(getEncryptButtonTitle());
	}

}
