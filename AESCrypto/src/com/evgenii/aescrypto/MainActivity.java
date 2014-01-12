package com.evgenii.aescrypto;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;

import com.evgenii.aescrypto.exceptions.InitialJsHasAlreadyBeenRun;
import com.evgenii.aescrypto.interfaces.JsCallback;

public class MainActivity extends Activity {

	public String currentDecryptMenuTitle;
	public WebView myWebView;
	public JavaScriptInterface myJSInterface;

	protected JsRunner jsRunner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		currentDecryptMenuTitle = getResources().getString(
				R.string.menu_decrypt_title);
		setContentView(R.layout.activity_main);

		jsRunner = new JsRunner(this);

		try {
			jsRunner.addInitialJs("function greet(name) { return 'Hello, ' + name; } ");
			jsRunner.runInitialJs();
		} catch (final InitialJsHasAlreadyBeenRun e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		menu.findItem(R.id.action_decrypt).setTitle(currentDecryptMenuTitle);
		return super.onPrepareOptionsMenu(menu);
	}

	public void onSendClicked(View view) {
		jsRunner.runJsFunction("greet", "Evgenii", new JsCallback() {
			@Override
			public void onResult(String value) {
				Log.d("ii", String.format("result of greet(): %s", value));
			}
		});
		currentDecryptMenuTitle = "Hey :)";
		invalidateOptionsMenu();
	}
}
