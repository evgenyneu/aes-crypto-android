package com.evgenii.aescrypto;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	public String currentDecryptMenuTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		currentDecryptMenuTitle = getResources().getString(
				R.string.menu_decrypt_title);
		setContentView(R.layout.activity_main);
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
		currentDecryptMenuTitle = "Hey :)";
		invalidateOptionsMenu();
	}
}
