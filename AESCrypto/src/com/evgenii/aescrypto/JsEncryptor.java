package com.evgenii.aescrypto;

import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;

import com.evgenii.aescrypto.interfaces.AssetsFileReaderInterface;
import com.evgenii.aescrypto.interfaces.JsEncryptorInterface;
import com.evgenii.jsevaluator.JsEvaluator;
import com.evgenii.jsevaluator.interfaces.JsCallback;
import com.evgenii.jsevaluator.interfaces.JsEvaluatorInterface;

/** Encrypts text using JavaScript library */
public class JsEncryptor implements JsEncryptorInterface {
	public static JsEncryptor evaluateAllScripts(Activity context) {
		final AssetsFileReader assetsFileReader = new AssetsFileReader(context);
		final JsEvaluator jsEvaluator = new JsEvaluator(context);
		final JsEncryptor jsEncryptor = new JsEncryptor(assetsFileReader, jsEvaluator);
		try {
			jsEncryptor.readScripts();
		} catch (final IOException e) {
			ShowFatalError.showAlertAndExit(context, "Can not read JavaScript file.", e);
		}
		jsEncryptor.evaluateScripts();
		return jsEncryptor;
	}

	private final AssetsFileReaderInterface mAssetsFileReader;
	private final JsEvaluatorInterface mJsEvaluator;
	private final String cryptoJsFileNames = "crypto_js";
	private final String aesCryptoFileName = "aes_crypto";
	private final String jsRootDir = "javascript";

	private static final String prefix = "AESCryptoV10";

	private ArrayList<String> mScriptsText;

	public JsEncryptor(AssetsFileReaderInterface assetsFileReader, JsEvaluatorInterface jsEvaluator) {
		mAssetsFileReader = assetsFileReader;
		mJsEvaluator = jsEvaluator;
	}

	@Override
	public void decrypt(String text, String password, JsCallback callback) {
		mJsEvaluator.callFunction(callback, "aesCrypto.decrypt", text, password);
	}

	@Override
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

	@Override
	public boolean isEncrypted(String text) {
		if (text == null)
			return false;

		return text.trim().startsWith(prefix);
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
