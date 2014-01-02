package com.evgenii.aescrypto;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import android.content.Context;
import android.content.res.AssetManager;

public class JSLoader {
	private final Context mContext;

	public JSLoader(Context context) {
		mContext = context;
	}

	public String ReadFile(String fileName) throws IOException {
		AssetManager am = mContext.getAssets();
		InputStream inputStream = am.open(fileName);

		return new Scanner(inputStream, "UTF-8").useDelimiter("\\A").next();
	}
}
