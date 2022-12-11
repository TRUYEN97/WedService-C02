package com.tec02.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpUtil  {
	private final String value;
	private final ObjectMapper objectMapper;
	public HttpUtil(String value) {
		this.value = value;
		this.objectMapper = new ObjectMapper();
		this.objectMapper.configure(
				DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false);
		this.objectMapper.configure(
				DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
	}
	
	public <T> T toModel(Class<T> clazz) {
		try {
			return objectMapper.readValue(value, clazz);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public <T> List<T> toModels() {
		try {
			return objectMapper.readValue(value, new TypeReference<List<T>>(){});
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static HttpUtil of(HttpServletRequest req) {
		try (BufferedReader reader = req.getReader()){
			StringBuilder builder = new StringBuilder();
			String line;
			while((line = reader.readLine())!= null) {
				builder.append(line);
			}
			return new HttpUtil(builder.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
