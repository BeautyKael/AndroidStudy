/*
 * @Title RulerSurfaceView.java
 * @Copyright Copyright 2010-2015 Careland Software Co,.Ltd All Rights Reserved.
 * @author Zhouls
 * @date Oct 8, 2016 10:53:51 AM
 * @version 1.0
 */
package ui.view;

import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

/**
 * Àà×¢ÊÍ
 * 
 * @author Zhouls
 * @date Oct 8, 2016 10:53:51 AM
 */
public class MySurfaceView extends SurfaceView implements
		SurfaceHolder.Callback {
	private Context mContext;
	private DrawThread drawThread;
	private SurfaceHolder holder;
	private int sw, sh;

	private void init(Context context) {
		mContext = context;
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		sh = wm.getDefaultDisplay().getHeight();
		sw = wm.getDefaultDisplay().getWidth();
	}

	/**
	 * @param context
	 */
	public MySurfaceView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context);
		holder = getHolder();
		holder.addCallback(this);
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public MySurfaceView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init(context);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public MySurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context);
	}

	/**
	 * @see android.view.SurfaceHolder.Callback#surfaceCreated(android.view.SurfaceHolder)
	 */
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		drawThread = new DrawThread(mContext);
		drawThread.start();
	}

	/**
	 * @see android.view.SurfaceHolder.Callback#surfaceChanged(android.view.SurfaceHolder,
	 *      int, int, int)
	 */
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see android.view.SurfaceHolder.Callback#surfaceDestroyed(android.view.SurfaceHolder)
	 */
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub

	}

	private class DrawThread extends Thread {

		public boolean isDestroy;
		private Paint mPaint;
		private Bitmap backBitmap;

		DrawThread(Context context) {
			isDestroy = false;
			mPaint = new Paint();
			try {
				backBitmap = BitmapFactory.decodeStream(context.getAssets()
						.open("back.jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		/**
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (!isDestroy) {
				draw();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		private void draw() {
			Canvas mCanvas = null;
			try {
				mCanvas = holder.lockCanvas();
				if (null != mCanvas) {
					mCanvas.drawColor(Color.WHITE);
					mPaint.setColor(Color.BLACK);
					mCanvas.drawBitmap(backBitmap, 0, 0, mPaint);
					Rect src = new Rect(0, 0, backBitmap.getWidth(),
							backBitmap.getHeight());
					Rect dst = new Rect(0, 0, sw, sh);
					mCanvas.drawBitmap(backBitmap, src, dst, mPaint);
					mPaint.setTextSize(25);
					mCanvas.drawText("15", 10, sh - 10, mPaint);
					holder.unlockCanvasAndPost(mCanvas);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
