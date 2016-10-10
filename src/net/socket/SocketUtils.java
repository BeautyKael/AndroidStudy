/*
 * @Title SocketUtils.java
 * @Copyright Copyright 2010-2015 Careland Software Co,.Ltd All Rights Reserved.
 * @author Zhouls
 * @date Oct 10, 2016 8:34:56 AM
 * @version 1.0
 */
package net.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import android.text.TextUtils;
import android.util.Log;

/**
 * socketUtils
 * 
 * @author Zhouls
 * @date Oct 10, 2016 8:34:56 AM
 */
public class SocketUtils {

	public static void client() {
		new ClientThread().start();
	}

	public static void server() {
		new ServerThread().start();
	}

	public static class ClientThread extends Thread {

		public boolean isDestroyed;
		private Socket clientSocket;
		private final String TAG = "client";

		public ClientThread() {

		}

		/**
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			while (!isDestroyed) {
				try {
					clientSocket = new Socket("127.0.0.1", 8822);
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (null != clientSocket) {
					try {
						OutputStream op = clientSocket.getOutputStream();
						String res = "curUtc:" + System.currentTimeMillis()
								/ 1000 + "\r\n";
						op.write(res.getBytes());
						op.flush();
						// BufferedReader br = new BufferedReader(
						// new InputStreamReader(
						// clientSocket.getInputStream()));
						// res = br.readLine();
						// if (!TextUtils.isEmpty(res)) {
						// Log.e(TAG, res);
						// }
						clientSocket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
	}

	public static class ServerThread extends Thread {
		public boolean isDestroyed;
		private ServerSocket serverSocket;
		private final String TAG = "server";

		public ServerThread() {
			isDestroyed = false;
		}

		/**
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			try {
				serverSocket = new ServerSocket(8822);
			} catch (IOException e) {
				e.printStackTrace();
			}
			while (!isDestroyed) {
				if (null != serverSocket) {
					try {
						Socket socket = serverSocket.accept();
						InputStream input = socket.getInputStream();
						BufferedReader br = new BufferedReader(
								new InputStreamReader(input));
						String res = br.readLine();
						if (!TextUtils.isEmpty(res)) {
							Log.e(TAG, res);
						}
						// OutputStream op = socket.getOutputStream();
						// BufferedWriter bw = new BufferedWriter(
						// new OutputStreamWriter(op));
						// bw.write("curUtc:" + System.currentTimeMillis() /
						// 1000
						// + "\n");
						socket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
	}

}
