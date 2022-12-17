package com.tec02.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
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
		this.objectMapper.configure(
				DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
		this.objectMapper.configure(
				DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, false);
		
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
	
	public void writeValue(OutputStream outputStream, Object object) throws JsonGenerationException, JsonMappingException, IOException {
		objectMapper.writeValue(outputStream, object);
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
