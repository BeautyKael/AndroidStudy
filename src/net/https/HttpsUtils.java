/*
 * @Title HttpsUtils.java
 * @Copyright Copyright 2010-2015 Careland Software Co,.Ltd All Rights Reserved.
 * @author Zhouls
 * @date Oct 10, 2016 5:30:48 PM
 * @version 1.0
 */
package net.https;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import android.content.Context;
import android.util.Log;

/**
 * https
 * 
 * @author Zhouls
 * @date Oct 10, 2016 5:30:48 PM
 */
public class HttpsUtils {

	public static void fuction(Context mContext) {
		HttpsURLConnection coon = null;
		InputStream inputStream = null;
		try {
			URL url = new URL("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxXXXXX&secret=1fd56a90sadfasdfxxfsdfsf");
			coon = (HttpsURLConnection) url.openConnection();

//			// 获取证书
//			CertificateFactory cFactory = CertificateFactory
//					.getInstance("X.509");
//			InputStream cerInputStream = mContext.getAssets().open("careland.cer");
//			Certificate cer = cFactory.generateCertificate(cerInputStream);
//			// 加载密钥
//			KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
//			keyStore.load(null, null);
//			keyStore.setCertificateEntry("cer", cer);
//			// 从证书里取信任列表
//			TrustManagerFactory tmf = TrustManagerFactory
//					.getInstance(TrustManagerFactory.getDefaultAlgorithm());
//			tmf.init(keyStore);
//			TrustManager[] trustManagers = tmf.getTrustManagers();
//			// TLS安全套接字
//			SSLContext sslContext = SSLContext.getInstance("TLS");
//			sslContext.init(null, trustManagers, null);
			
			SSLContext sc = SSLContext.getInstance("SSL");
			TrustManager[] tmArr = { new X509TrustManager() {
				@Override
				public void checkClientTrusted(
						X509Certificate[] paramArrayOfX509Certificate,
						String paramString) throws CertificateException {
				}

				@Override
				public void checkServerTrusted(
						X509Certificate[] paramArrayOfX509Certificate,
						String paramString) throws CertificateException {
				}

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			} };
			sc.init(null, tmArr, new SecureRandom());
			
			
			coon.setSSLSocketFactory(sc.getSocketFactory());
			
			
			
			
//			SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
//			// 设置安全套接字工厂
//			coon.setSSLSocketFactory(sslSocketFactory);
			
		
			
			inputStream = coon.getInputStream();

			BufferedReader bReader = new BufferedReader(new InputStreamReader(
					inputStream));
			StringBuilder sBuilder = new StringBuilder();
			String line;
			while (null != (line = bReader.readLine())) {
				sBuilder.append(line);
			}
			String res = sBuilder.toString();
			Log.d("debug", res);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	
	}
}
