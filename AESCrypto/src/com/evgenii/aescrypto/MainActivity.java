package com.evgenii.aescrypto;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MainActivity extends Activity {

	public String currentDecryptMenuTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		currentDecryptMenuTitle = getResources().getString(
				R.string.menu_decrypt_title);
		setContentView(R.layout.activity_main);

		WebView myWebView = (WebView) findViewById(R.id.myWebview);

		myWebView.loadData("", "text/html", null);
		myWebView.loadUrl("javascript: function wow(){return 'cuul!';}");
		myWebView.loadUrl("javascript: function oh(){return 'holy!';}");

		WebSettings webSettings = myWebView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		myWebView.addJavascriptInterface(new JavaScriptInterface(), "Android");
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
		WebView myWebView = (WebView) findViewById(R.id.myWebview);
		myWebView.loadUrl("javascript: Android.sendResult(wow());");
		myWebView.loadUrl("javascript: Android.sendResult(oh());");

		currentDecryptMenuTitle = "Hey :)";
		invalidateOptionsMenu();
	}
}
