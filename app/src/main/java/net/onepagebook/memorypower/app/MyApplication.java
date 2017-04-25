package net.onepagebook.memorypower.app;

import android.app.Application;
import android.content.Context;

import net.onepagebook.memorypower.BuildConfig;

import io.realm.Realm;
import timber.log.Timber;

public class MyApplication extends Application {
    private static Context context;

    public static Context getAppContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        Realm.init(this);
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
