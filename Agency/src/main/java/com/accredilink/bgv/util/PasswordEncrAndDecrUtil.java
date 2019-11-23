package com.accredilink.bgv.util;



import com.lambdaworks.crypto.SCryptUtil;

public class PasswordEncrAndDecrUtil {

	public static String encrypt(String password) {
		if (password != null) {
			return SCryptUtil.scrypt(password, 16, 16, 16);
		}
		return null;
	}
	
	public static boolean check(String password,String hashed) {
		boolean flag=false;
		if (password != null) {
			return SCryptUtil.check(password, hashed);
		}
		return flag;
	}
	
}
