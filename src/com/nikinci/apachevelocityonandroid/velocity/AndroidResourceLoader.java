package com.nikinci.apachevelocityonandroid.velocity;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.collections.ExtendedProperties;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.resource.Resource;
import org.apache.velocity.runtime.resource.loader.FileResourceLoader;

import android.content.res.AssetManager;
import android.content.res.Resources;

public class AndroidResourceLoader extends FileResourceLoader {
    private Resources resources;
    private String packageName;

    @Override
    public void commonInit(RuntimeServices rs, ExtendedProperties configuration) {
	super.commonInit(rs, configuration);
	this.resources = (Resources) rs.getProperty("android.content.res.Resources");
	this.packageName = (String) rs.getProperty("packageName");
    }

    public long getLastModified(Resource resource) {
	return 0;
    }

    public InputStream getResourceStream(String templateName) {

	// use this if html files under asset folder
	InputStream is = null;
	try {
	    is = resources.getAssets().open(templateName);
	} catch (IOException e) {
	    e.printStackTrace();
	}

	// use this if html files in raw folder under resources
	// int id = resources.getIdentifier(templateName, "raw",
	// this.packageName);
	// return resources.openRawResource(id);

	return is;
    }

    public boolean isSourceModified(Resource resource) {
	return false;
    }

    public boolean resourceExists(String templateName) {
	// use this if html files under asset folder
	AssetManager mg = resources.getAssets();
	try {
	    mg.open(templateName);
	    return true;
	} catch (IOException ex) {
	    ex.printStackTrace();
	    return false;
	}

	// use this if html files in raw folder under resources
	// return resources.getIdentifier(templateName, "raw", this.packageName)
	// != 0;

    }
}