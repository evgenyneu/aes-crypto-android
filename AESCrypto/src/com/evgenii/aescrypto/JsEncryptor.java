package com.evgenii.aescrypto;

import java.io.IOException;
import java.util.ArrayList;

import android.content.Context;

import com.evgenii.aescrypto.interfaces.AssetsFileReaderInterface;
import com.evgenii.jsevaluator.interfaces.JsCallback;
import com.evgenii.jsevaluator.interfaces.JsEvaluatorInterface;

/** Encrypts text using JavaScript library */
public class JsEncryptor {
	private final Context mContext;
	private final AssetsFileReaderInterface mAssetsFileReader;
	private final JsEvaluatorInterface mJsEvaluator;
	private final String cryptoJsFileNames = "crypto_js";
	private final String aesCryptoFileName = "aes_crypto";
	private final String jsRootDir = "javascript";
	private ArrayList<String> mScriptsText;

	public JsEncryptor(Context context, AssetsFileReaderInterface assetsFileReader,
			JsEvaluatorInterface jsEvaluator) {
		mContext = context;
		mAssetsFileReader = assetsFileReader;
		mJsEvaluator = jsEvaluator;

	}

	public void decrypt(String text, String password, JsCallback callback) {
		mJsEvaluator.callFunction(callback, "aesCrypto.decrypt", text, password);
	}

	public void encrypt(String text, String password, JsCallback callback) {
		mJsEvaluator.callFunction(callback, "aesCrypto.encrypt", text, password);
	}

	public void evaluateScripts() {
		final ArrayList<String> scripts = getScripts();
		for (final String scriptText : scripts) {
			mJsEvaluator.evaluate(scriptText);
		}
	}

	public ArrayList<String> getScripts() {
		if (mScriptsText == null) {
			mScriptsText = new ArrayList<String>();
		}
		return mScriptsText;
	}

	public void readScripts() throws IOException {
		final ArrayList<String> scriptsToLoad = new ArrayList<String>();

		scriptsToLoad.add(jsRootDir + "/" + cryptoJsFileNames + ".js");
		scriptsToLoad.add(jsRootDir + "/" + aesCryptoFileName + ".js");

		final ArrayList<String> scriptsText = getScripts();

		for (final String scriptName : scriptsToLoad) {
			scriptsText.add(mAssetsFileReader.ReadFile(scriptName));
		}
	}
}
