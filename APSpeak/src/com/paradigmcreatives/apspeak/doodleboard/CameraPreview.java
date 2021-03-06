package com.paradigmcreatives.apspeak.doodleboard;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Rect;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CameraPreview extends SurfaceView implements
		SurfaceHolder.Callback {

	private static final String TAG = "CameraPreview";

	private SurfaceHolder mHolder;
	private Camera mCamera;
	private List<Camera.Size> mSupportedPreviewSizes;
	private Camera.Size mPreviewSize;

	public CameraPreview(Context context, Camera camera) {
		super(context);
		mCamera = camera;

		if (mCamera != null) {
			// supported preview sizes
			mSupportedPreviewSizes = mCamera.getParameters()
					.getSupportedPreviewSizes();
			for (Camera.Size str : mSupportedPreviewSizes)
				Log.e(TAG, str.width + "/" + str.height);

			mCamera.setFaceDetectionListener(new FaceDetectionListenerImpl());
			// Install a SurfaceHolder.Callback so we get notified when the
			// underlying surface is created and destroyed.
			mHolder = getHolder();
			mHolder.addCallback(this);
			// deprecated setting, but required on Android versions prior to 3.0
			mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		}
	}

	private void setFeatures(Camera camera) {
		Camera.Parameters params = camera.getParameters();

		params.setPreviewSize(mPreviewSize.width, mPreviewSize.height);

		List<String> focusModes = params.getSupportedFocusModes();
		if (focusModes
				.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
			params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
		}

		if (params.getMaxNumMeteringAreas() > 0) { // check that metering areas
													// are supported
			List<Camera.Area> meteringAreas = new ArrayList<Camera.Area>();

			Rect areaRect1 = new Rect(-100, -100, 100, 100); // specify an area
																// in center of
																// image
			meteringAreas.add(new Camera.Area(areaRect1, 600)); // set weight to
																// 60%
			Rect areaRect2 = new Rect(800, -1000, 1000, -800); // specify an
																// area in upper
																// right of
																// image
			meteringAreas.add(new Camera.Area(areaRect2, 400)); // set weight to
																// 40%
			// params.setMeteringAreas(meteringAreas);
		}
		camera.setParameters(params);

	}

	public void surfaceCreated(SurfaceHolder holder) {
		// empty. surfaceChanged will take care of stuff
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		// empty. Take care of releasing the Camera preview in your activity.
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
		Log.e(TAG, "surfaceChanged => w=" + w + ", h=" + h);
		// If your preview can change or rotate, take care of those events here.
		// Make sure to stop the preview before resizing or reformatting it.
		if (mHolder.getSurface() == null) {
			// preview surface does not exist
			return;
		}

		// stop preview before making changes
		try {
			mCamera.stopPreview();
		} catch (Exception e) {
			// ignore: tried to stop a non-existent preview
		}

		// set preview size and make any resize, rotate or reformatting changes
		// here
		// start preview with new settings
		try {
			setFeatures(mCamera);
			mCamera.setDisplayOrientation(90);
			mCamera.setPreviewDisplay(mHolder);
			mCamera.startPreview();
			startFaceDetection();

		} catch (Exception e) {
			Log.d(TAG, "Error starting camera preview: " + e.getMessage());
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		final int width = resolveSize(getSuggestedMinimumWidth(),
				widthMeasureSpec);
		final int height = resolveSize(getSuggestedMinimumHeight(),
				heightMeasureSpec);

		if (mSupportedPreviewSizes != null) {
			mPreviewSize = getOptimalPreviewSize(mSupportedPreviewSizes, width,
					height);
		}

		float ratio;
		if (mPreviewSize.height >= mPreviewSize.width)
			ratio = (float) mPreviewSize.height / (float) mPreviewSize.width;
		else
			ratio = (float) mPreviewSize.width / (float) mPreviewSize.height;

		// One of these methods should be used, second method squishes preview
		// slightly
		setMeasuredDimension(width, (int) (width * ratio));
		// setMeasuredDimension((int) (width * ratio), height);
	}

	private static Camera.Size getOptimalPreviewSize(List<Camera.Size> sizes,
			int w, int h) {
		final double ASPECT_TOLERANCE = 0.1;
		double targetRatio = (double) h / w;

		if (sizes == null)
			return null;

		Camera.Size optimalSize = null;
		double minDiff = Double.MAX_VALUE;

		int targetHeight = h;

		for (Camera.Size size : sizes) {
			double ratio = (double) size.height / size.width;
			if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE)
				continue;

			if (Math.abs(size.height - targetHeight) < minDiff) {
				optimalSize = size;
				minDiff = Math.abs(size.height - targetHeight);
			}
		}

		if (optimalSize == null) {
			minDiff = Double.MAX_VALUE;
			for (Camera.Size size : sizes) {
				if (Math.abs(size.height - targetHeight) < minDiff) {
					optimalSize = size;
					minDiff = Math.abs(size.height - targetHeight);
				}
			}
		}

		return optimalSize;
	}

	public void startFaceDetection() {
		// Try starting Face Detection
		Camera.Parameters params = mCamera.getParameters();

		// start face detection only *after* preview has started
		if (params.getMaxNumDetectedFaces() > 0) {
			// camera supports face detection, so can start it:
			mCamera.startFaceDetection();
		}
	}
}