package com.zhouls.androidstudy;

import jni.Hello;
import net.https.HttpsUtils;
import net.socket.SocketUtils;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				HttpsUtils.fuction(getApplicationContext());
			}
		}).start();
	}

	static {
		System.loadLibrary("helloworld");
	}
}
