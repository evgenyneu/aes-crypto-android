package com.evgenii.aescrypto;

import java.util.ArrayList;

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
}
