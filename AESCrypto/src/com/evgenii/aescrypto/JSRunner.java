package com.evgenii.aescrypto;

import android.content.Context;

/**
 * Executes JavaScript code. Loads JS code. Calls a JavaScript function. Allows
 * to pass an argument to function call. Stores the function's return value.
 */
public class JSRunner {
	protected Context mContext;

	public JSRunner(Context context) {
		mContext = context;
	}

}
