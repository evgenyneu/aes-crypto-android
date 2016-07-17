package com.evgenii.aescrypto;

import android.app.Activity;
import android.support.v4.app.ShareCompat;

public class Share {
    public static void shareMessage(Activity context, String message) {
        ShareCompat.IntentBuilder
            .from(context)
            .setText(message)
            .setType("text/plain")
            .setChooserTitle("Share your message")
            .startChooser();
    }
}
