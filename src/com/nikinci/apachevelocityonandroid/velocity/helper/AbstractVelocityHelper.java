package com.nikinci.apachevelocityonandroid.velocity.helper;

import org.apache.velocity.VelocityContext;

import android.webkit.WebView;

public abstract class AbstractVelocityHelper {

    protected WebView webView;
    VelocityContext context;
    protected String templateUrl;

    public AbstractVelocityHelper(WebView webView, String templateUrl) {
	context = new VelocityContext();
	this.webView = webView;
	this.templateUrl = templateUrl;
    }

}
