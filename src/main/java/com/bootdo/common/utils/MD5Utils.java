package com.bootdo.common.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.security.MessageDigest;

public class MD5Utils {
	private static final String SALT = "1qazxsw2";

	private static final String ALGORITH_NAME = "md5";

	private static final int HASH_ITERATIONS = 2;

	/**
	 * 十六进制下数字到字符的映射数组
	 */
	private final static String[] hexDigits = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};

	public static String encrypt(String pswd) {
		String newPassword = new SimpleHash(ALGORITH_NAME, pswd, ByteSource.Util.bytes(SALT), HASH_ITERATIONS).toHex();
		return newPassword;
	}

	public static String encrypt(String username, String pswd) {
		String newPassword = new SimpleHash(ALGORITH_NAME, pswd, ByteSource.Util.bytes(username + SALT),
				HASH_ITERATIONS).toHex();
		return newPassword;
	}

	/**
	 * MD5加密
	 * @param val
	 * @return
	 */
	public static String simpleEncrypt(String val){
		if (val!=null) {
			try {
				//创建具有指定算法名称的信息摘要
				MessageDigest md5 = MessageDigest.getInstance("MD5");
				//使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
				byte[] results = md5.digest(val.getBytes());
				//将得到的字节数组变成字符串返回
				String result = byteArrayToHexString(results);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private static String byteArrayToHexString(byte[] b){
		StringBuffer resultSb = new StringBuffer();
		for(int i=0;i<b.length;i++){
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	private static String byteToHexString(byte b){
		int n = b;
		if(n<0)
			n=256+n;
		int d1 = n/16;
		int d2 = n%16;
		return hexDigits[d1] + hexDigits[d2];
	}
}
