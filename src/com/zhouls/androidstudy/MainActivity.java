package com.zhouls.androidstudy;

import jni.Hello;
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
		Intent intent = new Intent();
		intent.setAction("com.ui.animation.animationactivity");
		startActivity(intent);
		SocketUtils.server();
		SocketUtils.client();
		Hello hello = new Hello();
		long curUtc = System.currentTimeMillis();
		String a = hello.fromJniString();
		long nowUtc = System.currentTimeMillis();
		long dis = nowUtc - curUtc;
		Log.d("debug", "jni use" + dis);
		String b = "helloword";
		Log.d("debug", "java use" + (System.currentTimeMillis() - nowUtc));
		Toast.makeText(this, a, Toast.LENGTH_SHORT).show();
	}

	static {
		System.loadLibrary("helloworld");
	}
}
