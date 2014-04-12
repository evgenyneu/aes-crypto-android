package com.evgenii.aescrypto;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class ShowFatalError {
	public static void showAlertAndExit(final Activity activity, String message,
			final Exception e) {
		String errorMessage = e.getMessage();
		if (errorMessage.length() > 200) {
			errorMessage = errorMessage.substring(0, 199) + "...";
		}
		message = message + "\n\nDetails: " + errorMessage;
		new AlertDialog.Builder(activity).setTitle("Fatal Error").setMessage(message)
				.setCancelable(false)
				.setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						e.printStackTrace();
						activity.finish();
					}
				}).show();
	}
}
