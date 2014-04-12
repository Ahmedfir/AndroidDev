package com.example.simpleimagepicker.activities;

import java.io.Serializable;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.simpleimagepicker.R;
import com.example.simpleimagepicker.core.ApplicationProperties;
import com.exemple.simpleimagepicker.utils.AndroidUtil;
import com.exemple.simpleimagepicker.utils.DataUtil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;

/**
 * The main activity showing the chosen picture and the button to open the
 * ImagePicker
 * 
 * @author Ahmed
 * 
 */
public class MainActivity extends Activity {

	/**
	 * Simple name of the class for logging purpose.
	 */
	private static final String TAG = MainActivity.class.getSimpleName();

	/**
	 * request code to the activity profile pictures piker
	 * */
	private static final int IMAGE_PICKER_INTENT_CODE = 200;

	/**
	 * chosen picture view
	 * */
	private ImageView chosenPictureView;

	/**
	 * get the image loader
	 * */
	private ImageLoader imageLoader;

	/**
	 * options of the image loader
	 * */
	private DisplayImageOptions options;

	/**
	 * the click listener of the button to choose picture
	 */
	private OnClickListener choosePictureListener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			// check the presence of an SD card
			if (AndroidUtil.isSDPresent()) {

				// load the pictures url from the gallery
				Serializable picturesUrl = AndroidUtil
						.loadPicturesURLFromGallery(MainActivity.this);

				Intent intent = new Intent(MainActivity.this,
						ImagePickerActivity.class);

				intent.putExtra(
						ImagePickerActivity.IMAGE_PICKER_PICTURES_URLS_TAG,
						picturesUrl);

				startActivityForResult(intent, IMAGE_PICKER_INTENT_CODE);

			} else {

				// show message SD CARD NOT AVAILABLE
				Toast.makeText(MainActivity.this,
						R.string.ip_start_activity_sd_card_not_available,
						Toast.LENGTH_LONG).show();
			}
		}
	};

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// get the instance of the image loader
		imageLoader = ImageLoader.getInstance();

		// get the ImageView
		chosenPictureView = (ImageView) findViewById(R.id.ip_picture_choice_image_view);

		// show default picture
		showChosenPicture();

		// set the listener of the button
		findViewById(R.id.ip_picture_choice_text_view).setOnClickListener(
				choosePictureListener);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onActivityResult(int, int,
	 * android.content.Intent)
	 */
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// check the result is comming from the right intent
		if (requestCode == IMAGE_PICKER_INTENT_CODE) {

			if (resultCode == Activity.RESULT_OK) {
				// successfully picture token
				// from the profile album
				String picSelected = data
						.getStringExtra(ImagePickerActivity.IMAGE_PICKER_URL_TAG);
				// show the chosen picture
				showChosenPicture(picSelected);
			}

			else if ((resultCode == Activity.RESULT_CANCELED)) {
				// user cancelled Image capture
				Toast.makeText(MainActivity.this,
						"User cancelled image capture", Toast.LENGTH_SHORT)
						.show();
			}

		}
	}

	/**
	 * show the chosen picture
	 * 
	 * 
	 * @param String
	 *            ... url
	 */
	private void showChosenPicture(String... url) {

		String picSelected = ApplicationProperties.DEFAULT_CHOSEN_PICTURE_URL;
		if (DataUtil.isNotEmpty(url)) {
			picSelected = url[0];
		}

		// dislplay the image
		imageLoader.displayImage(picSelected, chosenPictureView, options,
				new SimpleImageLoadingListener() {
					@Override
					public void onLoadingComplete(String imageUri, View view,
							Bitmap loadedImage) {
						Animation anim = AnimationUtils.loadAnimation(
								MainActivity.this, android.R.anim.fade_in);
						chosenPictureView.setAnimation(anim);
						anim.start();
					}
				});
	}
}
