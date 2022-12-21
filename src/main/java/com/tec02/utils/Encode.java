package com.tec02.utils;

import java.util.Base64;

public class Encode {
	
	public static byte[] enCode(String value) {
		if (value == null) {
			return null;
		}
		return Base64.getEncoder().encode(value.getBytes());
	}
}
