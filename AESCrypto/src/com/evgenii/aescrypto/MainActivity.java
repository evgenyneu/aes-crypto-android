package com.evgenii.aescrypto;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.evgenii.jsevaluator.JsEvaluator;
import com.evgenii.jsevaluator.interfaces.JsCallback;

public class MainActivity extends Activity {

	public String currentDecryptMenuTitle;
	protected JsEvaluator mJsEvaluator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		currentDecryptMenuTitle = getResources().getString(
				R.string.menu_decrypt_title);
		setContentView(R.layout.activity_main);

		mJsEvaluator = new JsEvaluator(this);
		final AssetsFileReader fileReader = new AssetsFileReader(this);
		try {
			Log.d("ii", fileReader.ReadFile("javascript/aes_crypto.js"));
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		menu.findItem(R.id.action_decrypt).setTitle(currentDecryptMenuTitle);
		return super.onPrepareOptionsMenu(menu);
	}

	public void onSendClicked(View view) {
		mJsEvaluator.evaluate("2 * 17", new JsCallback() {
			@Override
			public void onResult(final String value) {
				currentDecryptMenuTitle = value;
				invalidateOptionsMenu();
			}
		});
	}
}
