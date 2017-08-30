package com.matsyuk.wizardcase;

import android.app.Application;

import com.matsyuk.wizardcase.di.ComponentManager;

/**
 * @author e.matsyuk
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ComponentManager.getInstance().initAppComponent();
    }

}
