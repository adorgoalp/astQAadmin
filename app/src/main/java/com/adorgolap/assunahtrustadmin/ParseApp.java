package com.adorgolap.assunahtrustadmin;

import android.app.Application;

/**
 * Created by ifta on 12/17/16.
 */

public class ParseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.initializeParse(getApplicationContext());
    }
}
