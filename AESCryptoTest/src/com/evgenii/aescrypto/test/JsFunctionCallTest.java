package com.evgenii.aescrypto.test;

import java.util.ArrayList;

import android.test.AndroidTestCase;

import com.evgenii.aescrypto.JsFunctionCall;

public class JsFunctionCallTest extends AndroidTestCase {
	public void testInit_shouldSetNameAndParams() {
		final ArrayList<Object> params = new ArrayList<Object>();
		params.add("milk");
		params.add(2);

		final JsFunctionCall jsFunctionCall = new JsFunctionCall("drink",
				params);

		assertEquals("drink", jsFunctionCall.getName());

		final ArrayList<Object> jsParams = jsFunctionCall.getParams();
		assertEquals(2, jsParams.size());
		assertEquals("milk", jsParams.get(0));
		assertEquals(2, jsParams.get(1));
	}
}
