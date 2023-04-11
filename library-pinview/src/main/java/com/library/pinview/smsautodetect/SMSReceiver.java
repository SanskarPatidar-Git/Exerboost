package com.library.pinview.smsautodetect;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class SMSReceiver extends BroadcastReceiver {

    private static final String TAG = SMSReceiver.class.getSimpleName();
    private SMSListener<String> callback;
    private String originatingAddress;

    /**
     * Set result callback
     *
     * @param callback SMSListener
     */
    public void setCallback(String originatingAddress, SMSListener<String> callback) {
        this.originatingAddress = originatingAddress;
        this.callback = callback;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        final Bundle bundle = intent.getExtras();
        try {
            if (bundle != null) {
                Object[] pdusObj = (Object[]) bundle.get("pdus");
                for (Object object : pdusObj) {
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) object);

                    String address = currentMessage.getDisplayOriginatingAddress();
                    String message = currentMessage.getDisplayMessageBody();
                    String otp = message.replaceAll("[^0-9]", "");

                    /*
                     * if null then get all messages
                     */
                    if (originatingAddress == null) {
                        callback.message(address, message);
                        callback.otp(otp);
                    }
                    /*
                     * only for specific messages
                     *
                     * provider WAYSMS
                     * or
                     * Specification contact number +919144453780
                     */
                    else if (address.contains(this.originatingAddress)) {
                        callback.message(address, message);
                        callback.otp(otp);
                    }
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "Exception smsReceiver" + e);
        }
    }
}