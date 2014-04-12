package com.example.simpleimagepicker.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.simpleimagepicker.R;
import com.example.simpleimagepicker.utils.DataUtil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;

/**
 * <p>
 * Image picker show a list of images by ArrayList<String> Urls, token from
 * Inetnt.
 * </p>
 * <p>
 * The Activity return the Url of the chosen image
 * </p>
 * <p>
 * called from CurrentActivity by Intent , with the ArrayList<String> as
 * Serializable in Extras :
 * </p>
 * 
 * <code>
 * Intent intent = new Intent(CurrentActivity.this, ImagePickerActivity.class);
 * 
 * intent.putExtra(ImagePickerActivity.IMAGE_PICKER_PICTURES_URLS_TAG, Serializable);
 * 
 * startActivityForResult(intent, int);
 * </code>
 * 
 * @author Ahmed
 * 
 */
public class ImagePickerActivity extends Activity implements
		OnItemClickListener {

	/**
	 * Simple name of the class for logging purpose.
	 */
	private static final String TAG = ImagePickerActivity.class.getSimpleName();

	/**
	 * tag for transferring the chosen picture by intent
	 * */
	public static final String IMAGE_PICKER_URL_TAG = "picSelected";

	/**
	 * tag for transferring the chosen picture by intent
	 * */
	public static final String IMAGE_PICKER_PICTURES_URLS_TAG = "pictureUrls";

	/**
	 * get the current instance of universal image loader
	 * */
	private ImageLoader imageLoader = ImageLoader.getInstance();

	/**
	 * ImageAdapter of the gridView
	 * */
	private ImageAdapter adapter;

	/**
	 * 
	 * options of the image loader
	 * */
	private DisplayImageOptions options;

	/**
	 * list of the profile pictures URL
	 * */
	private ArrayList<String> imageUrls;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_picker);

		Log.i(TAG, "picker lunched !");

		this.imageUrls = new ArrayList<String>();

		// get the Bundle from the intent
		Bundle extras = getIntent().getExtras();

		if (DataUtil.isNotEmpty(extras)) {

			// get the images' urls from the Bundle
			imageUrls = (ArrayList<String>) extras
					.getSerializable(IMAGE_PICKER_PICTURES_URLS_TAG);

			if (DataUtil.isEmpty(imageUrls)) {

				handleError();

			} else {

				GridView gridview = (GridView) findViewById(R.id.gridview);
				adapter = new ImageAdapter(this, imageUrls);
				gridview.setAdapter(adapter);
				gridview.setOnItemClickListener(this);

			}

		} else {
			handleError();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget
	 * .AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		// get the chosen picture Url
		String picUrl = adapter.getItem(position);
		Intent intent = new Intent();
		intent.putExtra(IMAGE_PICKER_URL_TAG, picUrl);
		setResult(Activity.RESULT_OK, intent);
		// return to the previous activity
		ImagePickerActivity.super.onBackPressed();
	}

	/**
	 * handle errors
	 * 
	 * @param errorMessage
	 */
	private void handleError(String... errorMessage) {

		final String defaultError = getResources().getString(
				R.string.ip_image_picker_activity_no_picture_found);

		String errorMsg = DataUtil.isEmpty(errorMessage) ? defaultError
				: defaultError + errorMessage[0];

		// show a toast error
		Toast.makeText(this, TAG + " : " + errorMsg, Toast.LENGTH_SHORT).show();

		// return to the previous activity
		ImagePickerActivity.super.onBackPressed();
	}

	// ====================================
	// The ImageAdapter for our grid view
	// ====================================

	private class ImageAdapter extends BaseAdapter {

		/**
		 * currnent Context
		 * */
		private Context currentContext;

		/**
		 * references to the picturesUrl
		 * */
		private ArrayList<String> picturesUrl = new ArrayList<String>();

		/**
		 * constructor
		 * */
		public ImageAdapter(Context c, ArrayList<String> imageList) {
			this.currentContext = c;
			this.picturesUrl = imageList;
		}

		/**
		 * create a new ImageView for each item referenced by the Adapter
		 * */
		public View getView(int position, View convertView, ViewGroup parent) {

			final ImageView imageView;

			if (convertView == null) {
				// if it's not recycled, initialize some attributes
				imageView = new ImageView(currentContext);
				imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
				imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
				imageView.setPadding(8, 8, 8, 8);
			} else {
				imageView = (ImageView) convertView;
			}

			imageLoader.displayImage(imageUrls.get(position), imageView,
					options, new SimpleImageLoadingListener() {
						@Override
						public void onLoadingComplete(String imageUri,
								View view, Bitmap loadedImage) {
							Animation anim = AnimationUtils.loadAnimation(
									ImagePickerActivity.this,
									android.R.anim.fade_in);
							imageView.setAnimation(anim);
							anim.start();
						}
					});
			return imageView;
		}

		/**
		 * get picture url from the grid View
		 * 
		 * @param int position
		 * @return String URL
		 * 
		 * @see android.widget.Adapter#getItem(int)
		 */
		public String getItem(int position) {
			return imageUrls.get(position);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.widget.Adapter#getCount()
		 */
		public int getCount() {
			return picturesUrl.size();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.widget.Adapter#getItemId(int)
		 */
		public long getItemId(int position) {
			return 0;
		}
	}

}
