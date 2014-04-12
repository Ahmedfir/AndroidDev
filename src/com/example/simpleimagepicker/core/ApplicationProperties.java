package com.example.simpleimagepicker.core;

import android.content.Context;

import com.example.simpleimagepicker.R;

/**
 * Constant definition of environment variables.
 * 
 * @author Ahmed
 * 
 */
public final class ApplicationProperties {

	/**
	 * The default chosen picture URL
	 * 
	 * @value String
	 * */
	public static final String DEFAULT_CHOSEN_PICTURE_URL = "http://www.greycardinals.com/wp-content/uploads/2012/09/Android-Mobile-Apps-Grey-Cardinal-Partners2.png";

	/**
	 * 
	 * @param context
	 * @return String Application Name
	 */
	public static String getApplicationName(Context context) {
		return context.getString(R.string.app_name);
	}
}
