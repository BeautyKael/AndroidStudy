/*
 * @Title SingleIntance.java
 * @Copyright Copyright 2010-2015 Careland Software Co,.Ltd All Rights Reserved.
 * @author Zhouls
 * @date Oct 12, 2016 2:34:55 PM
 * @version 1.0
 */
package designmode;

/**
 * ����ģʽ��̬�ڲ��ڼ���ʵ��
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
		// ��jvm ��֤SingleIntance Ψһ����
		private static SingleIntance instance;
		static {
			instance = new SingleIntance();
		}
	}
}
