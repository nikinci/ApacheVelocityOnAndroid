package com.nikinci.apachevelocityonandroid;

import org.apache.velocity.app.Velocity;

import android.app.Application;

public class ApplicationManager extends Application {

    private static ApplicationManager instance;

    @Override
    public void onCreate() {

	super.onCreate();
	setupVelocity();
	instance = this;
    }

    public static ApplicationManager getInstance() {
	return instance;
    }

    private void setupVelocity() {
	Velocity.setProperty(Velocity.RUNTIME_LOG_LOGSYSTEM_CLASS, "com.nikinci.apachevelocityonandroid.velocity.VelocityLogger");
	Velocity.setProperty("resource.loader", "android");
	Velocity.setProperty("android.resource.loader.class", "com.nikinci.apachevelocityonandroid.velocity.AndroidResourceLoader");
	Velocity.setProperty("android.content.res.Resources", getResources());
	Velocity.setProperty("packageName", "com.nikinci.apachevelocityonandroid");
	Velocity.setProperty("userdirective", "com.nikinci.apachevelocityonandroid.velocity.directives.TruncateDirective");
	Velocity.init();
    }
}
