package org.javasecret;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class DESEncry {

	private static final String ALGORITHM = "DES";
	private static Key key;
	private static final String KS_KEY = "this is your private key!";

	private DESEncry() {
	}

	static {
		try {
			KeyGenerator generator = KeyGenerator.getInstance(ALGORITHM);
			generator.init(new SecureRandom(KS_KEY.getBytes("UTF-8")));
			key = generator.generateKey();
		} catch (Exception e) {
			throw new RuntimeException("Error initializing DESCrypt. Cause: ",
					e);
		}
	}

	/**
	 * Encrypt
	 */
	public static String getEncString(String strMing) {
		byte[] byteMi = null;
		byte[] byteMing = null;
		String strMi = "";
		BASE64Encoder base64en = new BASE64Encoder();
		try {
			byteMing = strMing.getBytes("UTF-8");
			byteMi = getEncCode(byteMing);
			strMi = base64en.encode(byteMi);
		} catch (Exception e) {
			throw new RuntimeException("Error getEncString. Cause: ", e);
		} finally {
			base64en = null;
			byteMing = null;
			byteMi = null;
		}
		return strMi;
	}

	/**
	 * Decrypt
	 */
	public static String getDesString(String strMi) {
		BASE64Decoder base64De = new BASE64Decoder();
		byte[] byteMing = null;
		byte[] byteMi = null;
		String strMing = "";
		try {
			byteMi = base64De.decodeBuffer(strMi);
			byteMing = getDesCode(byteMi);
			strMing = new String(byteMing, "UTF-8");
		} catch (Exception e) {
			throw new RuntimeException("Error getDesString. Cause: ", e);
		} finally {
			base64De = null;
			byteMing = null;
			byteMi = null;
		}
		return strMing;
	}

	/**
	 * return Encrypt code
	 */
	private static byte[] getEncCode(byte[] byteS) {
		byte[] byteFina = null;
		Cipher cipher;
		try {
			cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byteFina = cipher.doFinal(byteS);
		} catch (Exception e) {
			throw new RuntimeException("Error getEncCode. Cause: ", e);
		} finally {
			cipher = null;
		}
		return byteFina;
	}

	/**
	 * return Decrypt code
	 */
	private static byte[] getDesCode(byte[] byteD) {
		Cipher cipher;
		byte[] byteFina = null;
		try {
			cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, key);
			byteFina = cipher.doFinal(byteD);
		} catch (Exception e) {
			throw new RuntimeException("Error getDesCode. Cause: ", e);
		} finally {
			cipher = null;
		}
		return byteFina;
	}

	
	public static void main(String args[]) {
		// data
		String str = "prov84101";
		System.out.println(str);


		// Encrypt
		String enc =DESEncry.getEncString(str);
		System.out.println("encrypted:" + enc);

		// Decrypt
		String dec = DESEncry.getDesString(enc);
		System.out.println("decrypted:" + dec);
	}
}
