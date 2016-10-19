/*
 * @Title SingleIntance.java
 * @Copyright Copyright 2010-2015 Careland Software Co,.Ltd All Rights Reserved.
 * @author Zhouls
 * @date Oct 12, 2016 2:34:55 PM
 * @version 1.0
 */
package designmode;

/**
 * 单例模式静态内部内加载实现
 * 
 * @author Zhouls
 * @date Oct 12, 2016 2:34:55 PM
 */
public class SingleIntance {

	private SingleIntance() {

	}

	public static SingleIntance getInstance() {
		return InnerInstance.instance;
	}

	private static class InnerInstance {
		// 由jvm 保证SingleIntance 唯一加载
		private static SingleIntance instance;
		static {
			instance = new SingleIntance();
		}
	}
}
