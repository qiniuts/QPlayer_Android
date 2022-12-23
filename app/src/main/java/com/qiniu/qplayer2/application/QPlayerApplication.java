package com.qiniu.qplayer2.application;

import android.app.Application;
import android.content.Context;
import androidx.multidex.MultiDex;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.tencent.bugly.crashreport.CrashReport;

public class QPlayerApplication extends Application {

        @Override
        public void onCreate() {
                super.onCreate();
                QPlayerApplicationContext.applicationContext = getApplicationContext();
                Glide.init(this, new GlideBuilder());
                CrashReport.initCrashReport(getApplicationContext(), "adadee00c2", false);
        }

        @Override
        public void attachBaseContext(Context base) {
                super.attachBaseContext(base);
                MultiDex.install(this);
        }
}