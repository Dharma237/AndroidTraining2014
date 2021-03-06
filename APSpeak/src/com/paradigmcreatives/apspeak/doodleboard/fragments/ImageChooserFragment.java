package com.paradigmcreatives.apspeak.doodleboard.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.paradigmcreatives.apspeak.R;
import com.paradigmcreatives.apspeak.doodleboard.ImageSelectionFragmentActivity;
import com.paradigmcreatives.apspeak.doodleboard.listeners.ImageSelectionOnClickListener;

public class ImageChooserFragment extends Fragment {
	private Fragment fragment;
	private LinearLayout mCameraLayout;
	private LinearLayout mGalleryLayout;
	private LinearLayout mBackgroundsLayout;
	private TextView mCameraText;
	private TextView mGalleryText;
	private TextView mBackgroundText;
	private FrameLayout childFrame;
	private ImageSelectionFragmentActivity activity;
	private String cueID;

	
	public ImageChooserFragment() {
		super();
	}
	
	public ImageChooserFragment(ImageSelectionFragmentActivity activity, String cueID) {
		this.activity = activity;
		this.cueID = cueID;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.image_selection_layout, container, false);
		mCameraLayout = (LinearLayout) view.findViewById(R.id.camera_button_layout);

		mGalleryLayout = (LinearLayout) view.findViewById(R.id.gallery_button_layout);
		mBackgroundsLayout = (LinearLayout) view.findViewById(R.id.background_button_layout);
		mCameraText = (TextView) view.findViewById(R.id.camera_text);
		mGalleryText = (TextView) view.findViewById(R.id.gallery_text);
		mBackgroundText = (TextView) view.findViewById(R.id.background_text);
		childFrame = (FrameLayout) view.findViewById(R.id.child_frame);


		/*
		 * mMainChildFrameLayout = (FrameLayout) findViewById(R.id.child_frame);
		 * mCameraFragmentLayout = (FrameLayout)
		 * findViewById(R.id.camera_frame);
		 */
		/*
		 * progressLayout = (RelativeLayout) findViewById(R.id.loadingPanel);
		 */
		ImageSelectionOnClickListener listener = new ImageSelectionOnClickListener(
				this);
		mCameraLayout.setOnClickListener(listener);
		mGalleryLayout.setOnClickListener(listener);
		mBackgroundsLayout.setOnClickListener(listener);
		showCameraFragment();
		
		return view;

	}
	
	@Override
	public void onResume() {
		super.onResume();
		showCameraFragment();
	}
	
	/**
	 * Show the camera fragment and start the camera
	 */
	public void showCameraFragment() {
		mCameraText.setTextColor(getResources().getColor(R.color.white));
		mCameraLayout.setBackgroundColor(getResources().getColor(
				R.color.tab_color));
		mGalleryText.setTextColor(getResources().getColor(R.color.tab_color));
		mGalleryLayout.setBackgroundColor(getResources()
				.getColor(R.color.white));
		mBackgroundText
				.setTextColor(getResources().getColor(R.color.tab_color));
		mBackgroundsLayout.setBackgroundColor(getResources().getColor(
				R.color.white));
		fragment = new CameraFragment(activity);
		FragmentTransaction transaction = getActivity().getSupportFragmentManager()
				.beginTransaction();
		if (fragment != null && transaction != null) {
			transaction.replace(R.id.child_frame, fragment, ImageSelectionFragmentActivity.CAMERA_TAG);
			transaction.addToBackStack(ImageSelectionFragmentActivity.CAMERA_TAG);
			transaction.commit();
		}
	}
	
	public void showGallery() {
		mGalleryText.setTextColor(getResources().getColor(R.color.white));
		mGalleryLayout.setBackgroundColor(getResources().getColor(
				R.color.tab_color));
		mCameraText.setTextColor(getResources().getColor(R.color.tab_color));
		mCameraLayout
				.setBackgroundColor(getResources().getColor(R.color.white));
		mBackgroundText
				.setTextColor(getResources().getColor(R.color.tab_color));
		mBackgroundsLayout.setBackgroundColor(getResources().getColor(
				R.color.white));
		fragment = new GalleryFragment(activity);
		FragmentTransaction transaction = getActivity().getSupportFragmentManager()
				.beginTransaction();
		if (fragment != null && transaction != null) {
			transaction.replace(R.id.child_frame, fragment, ImageSelectionFragmentActivity.GALLERY_TAG);
			transaction.addToBackStack(ImageSelectionFragmentActivity.GALLERY_TAG);
			transaction.commit();

		}

	}

	public void showBackgroundFragment() {
		mBackgroundText.setTextColor(getResources().getColor(R.color.white));
		mBackgroundsLayout.setBackgroundColor(getResources().getColor(
				R.color.tab_color));
		mCameraText.setTextColor(getResources().getColor(R.color.tab_color));
		mCameraLayout
				.setBackgroundColor(getResources().getColor(R.color.white));
		mGalleryText.setTextColor(getResources().getColor(R.color.tab_color));
		mGalleryLayout.setBackgroundColor(getResources()
				.getColor(R.color.white));
		fragment = new BackgroundFragment(activity, cueID);
		FragmentTransaction transaction = getActivity().getSupportFragmentManager()
				.beginTransaction();
		if (fragment != null && transaction != null) {
			transaction.replace(R.id.child_frame, fragment, ImageSelectionFragmentActivity.BACKGROUND_TAG);
			transaction.addToBackStack(ImageSelectionFragmentActivity.BACKGROUND_TAG);
			transaction.commit();

		}
	}


}
