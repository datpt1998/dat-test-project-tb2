package com.example.Main;

public class TestNative {
    static {
        System.loadLibrary("nativedatetimeutils");
    }

    public native String getSystemTime();
}
