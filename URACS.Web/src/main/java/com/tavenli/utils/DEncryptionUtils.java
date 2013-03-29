package com.tavenli.utils;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

public class DEncryptionUtils {


	public static String saltMD5Encoder(String rawStr){
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		//带盐值的MD5加密
		return encoder.encodePassword(rawStr, "salt");
	}
	
	public static String standPwdEncoder(String rawStr){
		StandardPasswordEncoder encoder = new StandardPasswordEncoder();
		return encoder.encode(rawStr);
	}
	
	
	public static void main(String[] args) {
		
		System.out.println(standPwdEncoder("test"));
		
	}
	
	
}
