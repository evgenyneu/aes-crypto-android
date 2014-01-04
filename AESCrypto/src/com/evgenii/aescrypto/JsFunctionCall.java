package com.evgenii.aescrypto;

import java.util.ArrayList;

/**
 * Stores information about JS function call: 1) name: JS function name 2)
 * params: array of parameters for the call. Parameter can be string or numeric
 */

public class JsFunctionCall {
	private final String mName;
	private final ArrayList<Object> mParams;

	public JsFunctionCall(String name, ArrayList<Object> params) {
		mName = name;
		mParams = params;
	}

	public String getName() {
		return mName;
	}

	public ArrayList<Object> getParams() {
		return mParams;
	}

	@Override
	public String toString() {
		final String str = mName;
		final ArrayList<String> paramsStr = new ArrayList<String>();

		for (final Object param : mParams) {

		}

		return str;
	}

	public static String paramToString(Object param) {
		String str = "";
		if (param instanceof String) {
			str = (String) param;
			str = str.replace("'", "\'");
			str = String.format("'%s'", str);
		} else {
			try {
				final double d = Double.parseDouble(param.toString());
				str = param.toString();
			} catch (final NumberFormatException nfe) {
			}
		}

		return str;
	}
}
