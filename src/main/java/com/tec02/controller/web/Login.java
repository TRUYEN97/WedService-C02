package com.tec02.controller.web;

import java.io.BufferedInputStream;
import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tec02.service.IUserService;

@WebServlet(urlPatterns = {"/api/v1/login"})
public class Login extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private IUserService userService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setStatus(200);
		resp.setHeader("Content-Type", "application/json");
		
		StringBuffer response = new StringBuffer();
		try (BufferedInputStream inputStream = new BufferedInputStream(req.getInputStream())){
			response.append(new String(inputStream.readAllBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			resp.getOutputStream().print(response.toString());
		}
	}
}
