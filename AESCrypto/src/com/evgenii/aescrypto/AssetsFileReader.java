package com.evgenii.aescrypto;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import android.content.Context;
import android.content.res.AssetManager;

import com.evgenii.aescrypto.interfaces.AssetsFileReaderInterface;

/** Reads text file to string from assets/ directory */
public class AssetsFileReader implements AssetsFileReaderInterface {
	private final Context mContext;

	public AssetsFileReader(Context context) {
		mContext = context;
	}

	public String ReadFile(String fileName) throws IOException {
		final AssetManager am = mContext.getAssets();
		final InputStream inputStream = am.open(fileName);

		return new Scanner(inputStream, "UTF-8").useDelimiter("\\A").next();
	}
}
