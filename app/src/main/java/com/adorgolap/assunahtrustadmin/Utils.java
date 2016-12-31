package com.adorgolap.assunahtrustadmin;

import android.content.Context;

import com.parse.Parse;

/**
 * Created by ifta on 12/3/16.
 */

public class Utils {
    public static void initializeParse(Context context) {
        //heroku parse config
//        Parse.initialize(new Parse.Configuration.Builder(context)
//                .applicationId("MB1TsAuUKlb7UgtB8egT")
//                .server("http://iparse.herokuapp.com/parse")
//                .enableLocalDataStore()
//                .build()
//        );
        //b4a parse config
        Parse.initialize(new Parse.Configuration.Builder(context)
                .applicationId("4qwrIVrsVl9wobCdTvLEqyR9kYK0a3T2vkXBI4A0")
                .clientKey("i7G2cuRxKa5mPRyxG5GpOcXPf3pPMbfBlNdPJp9D")
                .server("https://parseapi.back4app.com/").build()
        );
    }
}
