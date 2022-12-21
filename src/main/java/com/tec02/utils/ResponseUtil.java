package com.tec02.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

public class ResponseUtil {

	public static JSONObject createMessager( boolean status, Object data, String messs) {
		JSONObject response = new JSONObject();
		response.put("status", status);
		response.put("data", data);
		response.put("message", messs);
		return response;
	}

	public static boolean writeResponse(HttpServletResponse resp, JSONObject response) {
		if(resp == null || response == null) {
			return false;
		}
		try {
			resp.getWriter().write(response.toJSONString());
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
