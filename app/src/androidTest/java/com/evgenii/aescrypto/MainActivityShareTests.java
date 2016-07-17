package com.evgenii.aescrypto;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.EditText;

public class MainActivityShareTests extends ActivityInstrumentationTestCase2<MainActivity> {
    private MainActivity mActivity;

    public MainActivityShareTests() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mActivity = getActivity();
    }

    private void fillIn(int id, String text) throws InterruptedException {
        final EditText editText = (EditText) mActivity.findViewById(id);

        // Send string input value
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                editText.requestFocus();
            }
        });
        getInstrumentation().waitForIdleSync();
        getInstrumentation().sendStringSync(text);
        getInstrumentation().waitForIdleSync();

        Thread.sleep(100);
    }

    public void testShareButtonDisabledByDefault() {
        final View button = mActivity.findViewById(R.id.shareImageButton);
        assertEquals(0.3, button.getAlpha(), 0.001);
    }

    public void testShareButtonEnabledWhenMessageViewHasText() throws InterruptedException {
        fillIn(R.id.message, "Vegetables are green, red and orange. These are facts.");
        final View button = mActivity.findViewById(R.id.shareImageButton);
        assertEquals(1.0, button.getAlpha(), 0.001);
    }
}
