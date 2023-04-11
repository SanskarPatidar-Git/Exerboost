package com.library.pinview.smsautodetect;

import android.app.Activity;
import android.content.IntentFilter;

public class SMSAutodetect {

    private Activity activity;
    private SMSReceiver smsReceiver;

    private SMSListener<String> smsListener;
    private String originatingAddress;

    public SMSAutodetect(Activity activity, String originatingAddress, SMSListener<String> smsListener) {
        this.activity = activity;
        this.originatingAddress = originatingAddress;
        this.smsListener = smsListener;
    }

    private void registerReceiver() {
        smsReceiver = new SMSReceiver();
        smsReceiver.setCallback(originatingAddress, smsListener);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        activity.registerReceiver(smsReceiver, intentFilter);
    }

    public void onStart() {
        registerReceiver();
    }

    public void onStop() {
        activity.unregisterReceiver(smsReceiver);
        smsListener = null;
    }
}
