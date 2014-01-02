package com.evgenii.aescrypto.test;

import java.io.IOException;

import android.test.InstrumentationTestCase;

import com.evgenii.aescrypto.AssetsFileReader;

public class AssetsFileReaderTest extends InstrumentationTestCase {
	protected AssetsFileReader assetsFileReader;

	@Override
	protected void setUp() {
		assetsFileReader = new AssetsFileReader(getInstrumentation()
				.getContext());
	}

	public void testReadFile() throws IOException {
		String result = assetsFileReader
				.ReadFile("AssetsFileReader/read_file.txt");
		assertEquals("Hello\nWorld!\n日本語", result);
	}
}
