package com.example.deviceinfo;

import android.os.Build;

public class Info {

    private Build buildInfo = new Build();

    public String getHardwareInfo() {
        return buildInfo.HARDWARE;
    }
}
