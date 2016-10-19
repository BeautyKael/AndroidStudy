package ui.view.mapview;

import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
		// TODO �Զ����ɵĹ��캯�����
		init(context);
	}

	public MapView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO �Զ����ɵĹ��캯�����
		init(context);
	}

	public MapView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO �Զ����ɵĹ��캯�����
		init(context);
	}

	static final int NONE = 0;
	static final int DRAG = 1;
	static final int ZOOM = 2;

	int mode = NONE;
	private float oldDis = 0;
	private float newDis = 0;
	private PointF midPoint = null;
	Matrix savedMatrix = new Matrix();
	Matrix matrix = new Matrix();

	PointF dragStartP = new PointF();

	private void init(Context mContext) {
		this.mContext = mContext;
		try {
			bitmap = BitmapFactory.decodeStream(mContext.getAssets().open(
					"back.jpg"));
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View view, MotionEvent event) {
				// TODO �Զ����ɵķ������
				switch (event.getAction() & MotionEvent.ACTION_MASK) {
				case MotionEvent.ACTION_DOWN:
					// ���㰴��
					mode = DRAG;
					// �����ϴε�λ��
					savedMatrix.set(matrix);
					dragStartP.set(event.getX(), event.getY());
					break;
				case MotionEvent.ACTION_POINTER_UP:
				case MotionEvent.ACTION_UP:
					// �����ɿ� ˫���ɿ�
					mode = NONE;
					break;
				case MotionEvent.ACTION_POINTER_DOWN:
					// ��㰴��
					oldDis = placing(event);
					if (oldDis > 5f) {
						// 2ָ����>10�����㰴�� ��¼���µ�λ��
						savedMatrix.set(matrix);
						mode = ZOOM;
						midPoint = midPoint(event);
					}
					break;
				case MotionEvent.ACTION_MOVE:
					// �ƶ�
					if (mode == DRAG) {
						// ������ק
						matrix.set(savedMatrix);
						matrix.postTranslate(event.getX() - dragStartP.x,
								event.getY() - dragStartP.y);
					} else if (mode == ZOOM) {
						// ˫����ק
						newDis = placing(event);
						if (newDis > 5f) {
							// ���ŵľ���>10f���ػ�
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
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO �Զ����ɵķ������
		int width = 0;
		int height = 0;
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		int dirWidth = getPaddingLeft() + bitmap.getWidth() + getPaddingRight();
		int dirHeight = getPaddingTop() + bitmap.getHeight()
				+ getPaddingBottom();
		switch (widthMode) {
		case MeasureSpec.EXACTLY:
			// ��ȷֵ
			width = widthSize;
			break;
		case MeasureSpec.AT_MOST:
			// ����ܳ�������
			width = Math.min(dirWidth, widthSize);
			break;
		default:
			break;
		}
		switch (heightMode) {
		case MeasureSpec.EXACTLY:
			// ��ȷֵ
			height = heightSize;
			break;
		case MeasureSpec.AT_MOST:
			// ����ܳ�������
			height = Math.min(dirHeight, heightSize);
			break;
		default:
			break;
		}
		setMeasuredDimension(width, height);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO �Զ����ɵķ������
		super.onDraw(canvas);
		if (null != bitmap) {
			canvas.drawBitmap(bitmap, matrix, paint);
		}
	}

	public void setBitMap(Bitmap bitmap) {
		this.bitmap = bitmap;

	}

	/**
	 * ��2ָ֮��ľ���
	 * 
	 * @param motionEvent
	 * @return
	 */
	private float placing(MotionEvent motionEvent) {
		// TODO �Զ����ɵķ������
		float x = motionEvent.getX(0) - motionEvent.getX(1);
		float y = motionEvent.getY(0) - motionEvent.getY(1);
		return FloatMath.sqrt(x * x + y * y);
	}

	/**
	 * ��2ָ�е�
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
