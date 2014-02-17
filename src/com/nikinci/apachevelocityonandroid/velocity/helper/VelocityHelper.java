package com.nikinci.apachevelocityonandroid.velocity.helper;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.app.Velocity;

import android.webkit.WebView;

public class VelocityHelper extends AbstractVelocityHelper {

    public VelocityHelper(WebView webView, String templateUrl) {
	super(webView, templateUrl);
    }

    public void putData(String key, Object value) {
	// adding custom datas to context. That will be used in template html
	// files
	context.put(key, value);
    }

    public void render() {
	// finding template html file
	Template template = Velocity.getTemplate(templateUrl, "UTF-8");
	StringWriter sw = new StringWriter();
	// merging html with our datas
	template.merge(context, sw);
	// converting to it as a string
	String htmlData = sw.toString();
	// load html data to webview
	webView.loadData(htmlData, "text/html", "UTF-8");
    }

}
