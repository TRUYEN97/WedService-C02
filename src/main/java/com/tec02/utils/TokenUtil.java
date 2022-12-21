package com.tec02.utils;

import java.security.Key;
import java.util.Date;
import java.util.ResourceBundle;

import javax.crypto.spec.SecretKeySpec;

import com.tec02.common.AllKeyword;
import com.tec02.model.role.IRoleModel;
import com.tec02.model.user.IUserModel;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenUtil {

	public static String createJWT(IUserModel userModel, IRoleModel roleModel) {
		if (isAvaiable(userModel, roleModel)) {
			return null;
		}
		try {
			Class.forName("javax.xml.bind.DatatypeConverter");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
		String expConfig = ResourceBundle.getBundle("db").getString(AllKeyword.EXP_KEY);
		Key signingKey = new SecretKeySpec(getSecretKey(), signatureAlgorithm.getJcaName());
		JwtBuilder builder = Jwts.builder().setId(String.valueOf(userModel.getId()))
				.setIssuedAt(new Date())
				.setSubject(String.valueOf(roleModel.getCode()))
				.setIssuer(userModel.getUsername())
				.signWith(signatureAlgorithm, signingKey);
		long expMillis ;
		if (expConfig.matches("[0-9]+")) {
			expMillis = System.currentTimeMillis() + (Long.parseLong(expConfig)*1000);
		}else {
			expMillis = System.currentTimeMillis() + 36000000;
		}
		Date exp = new Date(expMillis);
		builder.setExpiration(exp);
		return builder.compact();
	}

	private static byte[] getSecretKey() {
		String key = ResourceBundle.getBundle("db").getString(AllKeyword.SECRET_KEY);
		return Encode.enCode(key);
	}
	
	public static Claims decodeJWT(String jwt) {
		try {
		    return Jwts.parser()
		            .setSigningKey(getSecretKey())
		            .parseClaimsJws(jwt).getBody();
		} catch (Exception e) {
			return null;
		}
	}

	private static boolean isAvaiable(IUserModel userModel, IRoleModel roleModel) {
		return userModel == null || userModel.getUsername().isBlank() || roleModel == null || roleModel.getCode() == 0;
	}
}
