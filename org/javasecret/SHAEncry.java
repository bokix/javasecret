package org.javasecret;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHAEncry {
	public static void main(String[] args) {
		SHAEncry sa = new SHAEncry();
		String source = "test msg.";
		String s = sa.encry(source);
		System.out.println(s);
	}

	public String encry(String source) {
		MessageDigest md = null;
		String tmpStr = null;

		try {
			md = MessageDigest.getInstance("SHA-1");
			byte[] digest = md.digest(source.getBytes());
			tmpStr = byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return tmpStr;
	}

	/**
	 * 将字节数组转换为十六进制字符串
	 * 
	 * @param byteArray
	 * @return
	 */
	private String byteToStr(byte[] byteArray) {
		String strDigest = "";
		for (int i = 0; i < byteArray.length; i++) {
			strDigest += byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}

	/**
	 * 将字节转换为十六进制字符串
	 * 
	 * @param mByte
	 * @return
	 */
	private String byteToHexStr(byte mByte) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
				'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = Digit[mByte & 0X0F];

		String s = new String(tempArr);
		return s;
	}
}
