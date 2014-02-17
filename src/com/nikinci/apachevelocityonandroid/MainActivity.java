package com.nikinci.apachevelocityonandroid;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.apachevelocityonandroid.R;
import com.nikinci.apachevelocityonandroid.model.BlogItem;
import com.nikinci.apachevelocityonandroid.velocity.helper.VelocityHelper;

public class MainActivity extends Activity {
    private WebView webViewVelocity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	setupViews();
	VelocityHelper vh = new VelocityHelper(webViewVelocity, "index.html");
	// adding custom datas and classes
	vh.putData("StringUtils", org.apache.commons.lang.StringUtils.class);
	vh.putData("items", prepareBlogItems());
	vh.putData("menuItems", prepareMenuItems());
	vh.putData("customDirectiveEx", "Custom Directive example text, it will be truncated");

	vh.render();
    }

    private List<String> prepareMenuItems() {
	List<String> items = new ArrayList<String>();

	String item;
	for (int i = 0; i < 10; i++) {
	    item = "menuItem : " + i;
	    items.add(item);
	}
	return items;
    }

    private List<BlogItem> prepareBlogItems() {
	List<BlogItem> items = new ArrayList<BlogItem>();

	BlogItem item;
	for (int i = 0; i < 10; i++) {
	    item = new BlogItem(
		    "Title : " + i,
		    "http://velocity.apache.org/images/velocity_project_wide.png",
		    "content : "
			    + i
			    + "\n Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
	    items.add(item);
	}
	return items;
    }

    private void setupViews() {

	webViewVelocity = (WebView) findViewById(R.id.webView);
	webViewVelocity.getSettings().setJavaScriptEnabled(true);
    }

}
