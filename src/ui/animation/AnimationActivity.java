/*
 * @Title AnimationActivity.java
 * @Copyright Copyright 2010-2015 Careland Software Co,.Ltd All Rights Reserved.
 * @author Zhouls
 * @date Oct 9, 2016 3:33:52 PM
 * @version 1.0
 */
package ui.animation;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.zhouls.androidstudy.R;

/**
 * Àà×¢ÊÍ
 * 
 * @author Zhouls
 * @date Oct 9, 2016 3:33:52 PM
 */
public class AnimationActivity extends Activity {
	private int sH, sW;

	/**
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		sH = wm.getDefaultDisplay().getHeight();
		sW = wm.getDefaultDisplay().getWidth();
		setContentView(R.layout.activity_animation);
		ImageView imageView = (ImageView) findViewById(R.id.anim1);
		imageView.setBackgroundResource(R.drawable.ic_launcher);
	}

	public void onClickAnima1(View v) {
		ObjectAnimator.ofFloat(v, "rotationX", 0.0F, 360.0F).setDuration(500)
				.start();
	}

	public void onClickAnima2(View v) {
		ObjectAnimator.ofFloat(v, "alpha", 0.0F, 1.0F).setDuration(500).start();
	}

	public void onClickAnima3(View v) {
		ObjectAnimator
				.ofFloat(v, "Y", v.getY(),
						(float) (v.getY() + Math.random() * 100))
				.setDuration(1500).start();
	}

	public void onClickAnima4(View v) {
		PropertyValuesHolder a1 = PropertyValuesHolder.ofFloat("X", 0,
				sW - v.getWidth() / 2);
		PropertyValuesHolder a2 = PropertyValuesHolder.ofFloat("Y",
				v.getHeight(), sH);
		ObjectAnimator.ofPropertyValuesHolder(v, a1, a2).setDuration(1000)
				.start();
	}
}
