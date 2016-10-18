package com.zhouls.androidstudy;

import java.io.IOException;

import ui.view.mapview.MapView;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		MapView mapView = (MapView) findViewById(R.id.mapview);
		Bitmap bitmap;
		try {
			bitmap = BitmapFactory.decodeStream(getAssets().open("back.jpg"));
			mapView.setBitMap(bitmap);
			mapView.postInvalidate();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}
}
