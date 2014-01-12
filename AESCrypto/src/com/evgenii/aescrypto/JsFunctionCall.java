package com.evgenii.aescrypto;

import java.util.ArrayList;

/**
 * Stores information about JS function call: 1) name: JS function name 2)
 * params: array of parameters for the call. Parameter can be string or numeric
 */

public class JsFunctionCall {
	private final String mName;
	private final ArrayList<Object> mParams;
	private final Integer mCallbackIndex;

	public JsFunctionCall(String name, ArrayList<Object> params,
			Integer callbackIndex) {
		mName = name;
		mParams = params;
		mCallbackIndex = callbackIndex;
	}

	public String getName() {
		return mName;
	}

	public ArrayList<Object> getParams() {
		return mParams;
	}

	public Integer getCallbackIndex() {
		return mCallbackIndex;
	}

	@Override
	public String toString() {
		final StringBuilder paramsStr = new StringBuilder();

		for (final Object param : mParams) {
			if (paramsStr.length() > 0) {
				paramsStr.append(", ");
			}

			paramsStr.append(paramToString(param));
		}

		if (paramsStr.length() > 0) {
			paramsStr.append(", ");
		}

		paramsStr.append(paramToString(getCallbackIndex()));

		return String.format("%s(%s)", mName, paramsStr);
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
