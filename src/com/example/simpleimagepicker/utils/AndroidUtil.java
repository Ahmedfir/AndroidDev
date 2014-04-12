package com.example.simpleimagepicker.utils;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

public final class AndroidUtil {

	/**
	 * Simple name of the class for logging purpose.
	 */
	private static final String TAG = AndroidUtil.class.getSimpleName();

	/**
	 * Internal constructor; not to be called as this class provides static
	 * utilities only.
	 */
	private AndroidUtil() {
		throw new UnsupportedOperationException("No instances permitted");
	}

	/**
	 * load the URLs of pictures from the Gallery
	 * 
	 * 
	 * @return ArrayList<String> URLs of the pictures from the gallery
	 */
	public static ArrayList<String> loadPicturesURLFromGallery(Context ctx) {

		ArrayList<String> galleryImageUrls = new ArrayList<String>();

		final String[] PICTURES_STORAGE_COLUMNS = { MediaStore.Images.Media.DATA };

		final String orderByDtaes = MediaStore.Images.Media.DATE_TAKEN
				+ " DESC";

		Cursor imageCursor = ctx.getContentResolver().query(
				MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
				PICTURES_STORAGE_COLUMNS, null, null, orderByDtaes);

		if (imageCursor.moveToFirst()) {

			for (imageCursor.moveToFirst(); !imageCursor.isAfterLast(); imageCursor
					.moveToNext()) {
				int dataColumnIndex = imageCursor
						.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
				galleryImageUrls.add("file://"
						+ imageCursor.getString(dataColumnIndex));
			}

		}

		return galleryImageUrls;
	}

	/**
	 * check if the SD card is mounted
	 * 
	 * @return boolean
	 * */
	public static boolean isSDPresent() {
		return android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);
	}

}
