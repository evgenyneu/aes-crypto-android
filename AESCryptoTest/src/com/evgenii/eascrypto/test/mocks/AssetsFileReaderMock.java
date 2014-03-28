package com.evgenii.eascrypto.test.mocks;

import java.io.IOException;

import com.evgenii.aescrypto.interfaces.AssetsFileReaderInterface;

public class AssetsFileReaderMock implements AssetsFileReaderInterface {
	@Override
	public String ReadFile(String fileName) throws IOException {
		return fileName + " script";
	}

}
