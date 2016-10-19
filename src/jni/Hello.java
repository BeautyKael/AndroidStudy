/*
 * @Title Hello.java
 * @Copyright Copyright 2010-2015 Careland Software Co,.Ltd All Rights Reserved.
 * @author Zhouls
 * @date Oct 11, 2016 11:11:04 AM
 * @version 1.0
 */
package jni;

/**
 * Àà×¢ÊÍ
 * 
 * @author Zhouls
 * @date Oct 11, 2016 11:11:04 AM
 */
public class Hello {
	private String head = "Head";

	private static String staticHead = "StaticHead";

	public void setHead(String head) {
		this.head = head;
	}

	public static void setStaticHead(String head) {
		staticHead = head;
	}

	public native String fromJniString();

	public native int fromJniInt();
}
