package com.evgenii.aescrypto;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.evgenii.jsevaluator.interfaces.JsCallback;

public class MainActivity extends Activity {

	public String currentDecryptMenuTitle;
	protected JsEncryptor mJsEncryptor;

	public JsEncryptor getEncryptor() {
		return mJsEncryptor;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		currentDecryptMenuTitle = getResources().getString(R.string.menu_decrypt_title);
		setContentView(R.layout.activity_main);

		mJsEncryptor = JsEncryptor.evaluateAllScripts(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onEncryptClicked() {
		final EditText editText = (EditText) findViewById(R.id.message);
		final String message = editText.getText().toString();
		mJsEncryptor.encrypt(message, "test", new JsCallback() {
			@Override
			public void onResult(final String value) {
				mJsEncryptor.decrypt(value, "test", new JsCallback() {
					@Override
					public void onResult(final String valueDecrypted) {
						editText.setText(valueDecrypted + "!!!");
					}
				});
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

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		menu.findItem(R.id.action_decrypt).setTitle(currentDecryptMenuTitle);
		return super.onPrepareOptionsMenu(menu);
	}
}
