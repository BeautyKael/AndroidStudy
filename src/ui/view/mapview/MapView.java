package ui.view.mapview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MapView extends ImageView {

	private Context mContext;
	private Bitmap bitmap;

	public MapView(Context context) {
		super(context);
		// TODO 自动生成的构造函数存根
		init(context);
	}

	public MapView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO 自动生成的构造函数存根
		init(context);
	}

	public MapView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO 自动生成的构造函数存根
		init(context);
	}

	static final int NONE = 0;
	static final int DRAG = 0;
	static final int ZOOM = 0;

	int mode = NONE;
	private float oldDis = 0;
	private float newDis = 0;
	private PointF midPoint = null;
	Matrix savedMatrix = new Matrix();
	Matrix matrix = new Matrix();

	PointF dragStartP = new PointF();

	private void init(Context mContext) {
		this.mContext = mContext;
		setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View view, MotionEvent event) {
				// TODO 自动生成的方法存根

				switch (event.getAction() & MotionEvent.ACTION_MASK) {
				case MotionEvent.ACTION_DOWN:
					// 单点按下
					mode = DRAG;
					// 保存上次的位置
					savedMatrix.set(matrix);
					dragStartP.set(event.getX(), event.getY());
					break;
				case MotionEvent.ACTION_POINTER_UP:
				case MotionEvent.ACTION_UP:
					// 单点松开 双点松开
					mode = NONE;
					break;
				case MotionEvent.ACTION_POINTER_DOWN:
					// 多点按下
					oldDis = placing(event);
					if (oldDis > 5f) {
						// 2指距离>10才算多点按下 记录按下的位置
						savedMatrix.set(matrix);
						mode = ZOOM;
						midPoint = midPoint(event);
					}
					break;
				case MotionEvent.ACTION_MOVE:
					// 移动
					if (mode == DRAG) {
						// 单点拖拽
						matrix.set(savedMatrix);
						matrix.postTranslate(event.getX() - dragStartP.x,
								event.getY() - dragStartP.y);
					} else if (mode == ZOOM) {
						// 双点拖拽
						newDis = placing(event);
						if (newDis > 5f) {
							// 缩放的距离>10f才重绘
							float zoomScale = newDis / oldDis;
							Log.d("debug", zoomScale + "");
							matrix.set(savedMatrix);
							matrix.postScale(zoomScale, zoomScale, midPoint.x,
									midPoint.y);
						}
					}
					break;
				}
				setImageMatrix(matrix);
				return true;
			}

		});

	}

	Paint paint = new Paint();

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO 自动生成的方法存根
		super.onDraw(canvas);
		if (null != bitmap) {
			canvas.drawBitmap(bitmap, matrix, paint);
		}
	}

	public void setBitMap(Bitmap bitmap) {
		this.bitmap = bitmap;

	}

	/**
	 * 算2指之间的距离
	 * 
	 * @param motionEvent
	 * @return
	 */
	private float placing(MotionEvent motionEvent) {
		// TODO 自动生成的方法存根
		float x = motionEvent.getX(0) - motionEvent.getX(1);
		float y = motionEvent.getY(0) - motionEvent.getY(1);
		return FloatMath.sqrt(x * x + y * y);
	}

	/**
	 * 算2指中点
	 * 
	 * @param motionEvent
	 * @return
	 */
	private PointF midPoint(MotionEvent motionEvent) {
		float x = motionEvent.getX(0) + motionEvent.getX(1);
		float y = motionEvent.getY(0) + motionEvent.getY(1);
		return new PointF(x / 2, y / 2);
	}
}
