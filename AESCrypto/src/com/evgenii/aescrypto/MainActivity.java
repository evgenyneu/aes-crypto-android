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

		// myWebView = new WebView(this);
		//
		// myWebView.loadData("", "text/html", null);
		// myWebView.loadUrl("javascript: function wow(){return 'cuul!';}");
		// myWebView.loadUrl("javascript: function oh(){return 'holy!';}");
		//
		// final WebSettings webSettings = myWebView.getSettings();
		// webSettings.setJavaScriptEnabled(true);
		// myJSInterface = new JavaScriptInterface();
		// myWebView.addJavascriptInterface(myJSInterface, "Android");

		jsRunner = new JsRunner(this);

		try {
			jsRunner.addInitialJs("function myInitial() { return 'Hello from intial'; } ");
			Log.d("ii", "addInitialJs()");
			jsRunner.runInitialJs();
			Log.d("ii", "runInitialJs()");
		} catch (final InitialJsHasAlreadyBeenRun e) {
			Log.e("ii", "Error in initial JS");
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
		Log.d("ii", currentDecryptMenuTitle);
		return super.onPrepareOptionsMenu(menu);
	}

	public void onSendClicked(View view) {
		// myWebView.loadUrl("javascript: Android.sendResult(wow());");
		// myWebView.loadUrl("javascript: Android.sendResult(oh());");
		// myWebView
		// .loadUrl("javascript: Android.sendResult(Android.getString());");
		//
		// myWebView
		// .loadUrl("javascript: Android.sendResult(Android.getString());");

		jsRunner.runJsFunction("myInitial", "", new JsCallback() {
			@Override
			public void onResult(String value) {
				Log.d("ii", "result of myInitial()");
				Log.d("ii", value);
			}
		});
		currentDecryptMenuTitle = "Hey :)";
		invalidateOptionsMenu();
	}
}
