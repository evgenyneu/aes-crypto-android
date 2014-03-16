package com.evgenii.eascrypto.test.mocks;

import java.io.IOException;
import java.util.ArrayList;

import com.evgenii.aescrypto.interfaces.AssetsFileReaderInterface;

public class AssetsFileReaderMock implements AssetsFileReaderInterface {
	public ArrayList<String> mFilesRead;

	@Override
	public String ReadFile(String fileName) throws IOException {
		mFilesRead.add(fileName);
		return fileName + " script";
	}

}
