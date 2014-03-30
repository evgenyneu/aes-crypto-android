package com.evgenii.aescrypto;

import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.evgenii.jsevaluator.JsEvaluator;
import com.evgenii.jsevaluator.interfaces.JsCallback;

public class MainActivity extends Activity {

	public String currentDecryptMenuTitle;
	protected JsEncryptor mJsEncryptor;

	public void evaluateScripts() {
		final AssetsFileReader assetsFileReader = new AssetsFileReader(this);
		final JsEvaluator jsEvaluator = new JsEvaluator(this);
		mJsEncryptor = new JsEncryptor(this, assetsFileReader, jsEvaluator);
		try {
			mJsEncryptor.readScripts();
		} catch (final IOException e) {
			showFataErrorAlertAndExit("Can not read JavaScript file.", e);
		}
		mJsEncryptor.evaluateScripts();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		currentDecryptMenuTitle = getResources().getString(R.string.menu_decrypt_title);
		setContentView(R.layout.activity_main);

		evaluateScripts();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onEncryptClicked() {
		Log.d("ii", "onEncryptClicked");
		mJsEncryptor.encrypt("hello word", "test", new JsCallback() {
			@Override
			public void onResult(final String value) {
				Log.d("ii", "onResult");
				Log.d("ii", value);
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

	public void showFataErrorAlertAndExit(String message, final Exception e) {
		String errorMessage = e.getMessage();
		if (errorMessage.length() > 200) {
			errorMessage = errorMessage.substring(0, 199) + "...";
		}
		message = message + "\n\nDetails: " + errorMessage;
		new AlertDialog.Builder(this).setTitle("Fatal Error").setMessage(message)
				.setCancelable(false)
				.setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						e.printStackTrace();
						finish();
					}
				}).show();
	}
}
