package com.evgenii.eascrypto.test.mocks;

import com.evgenii.aescrypto.interfaces.MainActivityInterface;

public class MainActivityMock implements MainActivityInterface {

	@Override
	public boolean hasMessage() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasPassword() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void invalidateOptionsMenu() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isBusy() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setMessage(String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public String trimmedMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String trimmedPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateBusy(boolean isBusy) {
		// TODO Auto-generated method stub

	}

}
