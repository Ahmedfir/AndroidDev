/**
 * 
 */
package com.example.simpleimagepicker.core;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * <p>
 * Extend base {@link android.app.Application} to initialize the application
 * before any activity, service, or receiver objects (excluding content
 * providers) have been created.
 * </p>
 * 
 * <p>
 * Use this class as singleton.
 * <code>SimpleImagePickerApplication simpleIP = (SimpleImagePickerApplication) getApplication();</code>
 * </p>
 * 
 * @author Ahmed
 * 
 */
public class SimpleImagePickerApplication extends Application {

	/**
	 * Simple name of the class for logging purpose.
	 */
	private static final String TAG = SimpleImagePickerApplication.class
			.getSimpleName();

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Application#onCreate()
	 */
	@Override
	public void onCreate() {
		super.onCreate();
		Log.i(TAG, "Create SimpleImagePicker application.");

		this.initializeThirdParty();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Application#onTerminate()
	 */
	@Override
	public void onTerminate() {
		Log.i(TAG, "Terminate SimpleImagePicker application.");

		ImageLoader.getInstance().destroy();
		super.onTerminate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Application#onConfigurationChanged(android.content.res.
	 * Configuration)
	 */
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	/**
	 * Initialize third party libraries.
	 */
	private void initializeThirdParty() {

		// set the configuration of the image loader
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
				.cacheInMemory(true).cacheOnDisc(true).build();
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				getApplicationContext()).defaultDisplayImageOptions(
				defaultOptions).build();
		// initiate the image loader
		ImageLoader.getInstance().init(config);
	}
}
