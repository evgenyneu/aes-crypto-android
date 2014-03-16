package com.evgenii.aescrypto;

import java.io.IOException;

import android.content.Context;

import com.evgenii.aescrypto.interfaces.AssetsFileReaderInterface;
import com.evgenii.jsevaluator.interfaces.JsEvaluatorInterface;

/** Encrypts text using JavaScript library */
public class JsEncryptor {
	private final Context mContext;
	private final AssetsFileReaderInterface mAssetsFileReader;
	private final JsEvaluatorInterface mJsEvaluator;

	public JsEncryptor(Context context,
			AssetsFileReaderInterface assetsFileReader,
			JsEvaluatorInterface jsEvaluator) {
		mContext = context;
		mAssetsFileReader = assetsFileReader;
		mJsEvaluator = jsEvaluator;
	}

	private void LoadScripts() throws IOException {
		mAssetsFileReader.ReadFile("one");
		mAssetsFileReader.ReadFile("two");
	}
}
