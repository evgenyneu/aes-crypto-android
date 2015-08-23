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

	@Override
	public String ReadFile(String fileName) throws IOException {
		final AssetManager am = mContext.getAssets();
		final InputStream inputStream = am.open(fileName);

		final Scanner scanner = new Scanner(inputStream, "UTF-8");
		final String text = scanner.useDelimiter("\\A").next();
		scanner.close();
		return text;
	}
}
