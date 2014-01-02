package com.evgenii.aescrypto.test;

import java.io.IOException;

import android.test.InstrumentationTestCase;

import com.evgenii.aescrypto.JSLoader;

public class JSLoaderTest extends InstrumentationTestCase {
	protected JSLoader jsLoader;

	@Override
	protected void setUp() {
		jsLoader = new JSLoader(getInstrumentation().getContext());
	}

	public void testReadFile() throws IOException {
		String result = jsLoader.ReadFile("JSLoaderTest/read_file.txt");
		assertEquals("Hello\nWord!\n日本語", result);
	}
}
