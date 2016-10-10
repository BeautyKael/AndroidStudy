/*
 * @Title OkHttpUtils.java
 * @Copyright Copyright 2010-2015 Careland Software Co,.Ltd All Rights Reserved.
 * @author Zhouls
 * @date Oct 9, 2016 11:08:37 AM
 * @version 1.0
 */
package net.http;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * OKHttpUtils
 * 
 * @author Zhouls
 * @date Oct 9, 2016 11:08:37 AM
 */
public class OkHttpUtils {
	/**
	 * work
	 * 
	 * @return void
	 * @author Zhouls
	 * @date Oct 9, 2016 11:13:18 AM
	 */
	public static void fuction() {
		OkHttpClient mOkHttpClient = new OkHttpClient();
		final Request request = new Request.Builder().url(
				"http://www.baidu.com").build();
		Call call = mOkHttpClient.newCall(request);
		call.enqueue(new Callback() {

			@Override
			public void onFailure(Call arg0, IOException arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onResponse(Call arg0, Response arg1) throws IOException {
				// TODO Auto-generated method stub
				String res = arg1.body().string();
				res.length();
			}

		});
	}
}
