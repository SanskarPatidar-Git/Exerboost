package com.library.pinview.smsautodetect;

public interface SMSListener<T> {
    void message(String address, String message);
    void otp(String otp);
}
