package com.thebay.thebay1;

import android.app.Application;

import com.tsengvn.typekit.Typekit;

/**
 * Created by kyoungae on 2017-10-16.
 */

public class CustomStartApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Typekit.getInstance()
                .addNormal(Typekit.createFromAsset(this,"fonts/NanumGothic.ttf"))
                .addBold(Typekit.createFromAsset(this,"fonts/NanumGothicBold.ttf"))
                .addCustom1(Typekit.createFromAsset(this,"fonts/NanumGothicExtraBold.ttf"));

    }
}
