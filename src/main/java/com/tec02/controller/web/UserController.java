package com.tec02.controller.web;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.tec02.model.user.IUserModel;
import com.tec02.service.IUserService;
import com.tec02.utils.HttpUtil;

@WebServlet(urlPatterns = { "/api/v1/user" })
public class UserController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private IUserService userService;

	@Inject
	private IUserModel userModel;
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setStatus(200);
		resp.setCharacterEncoding("UTF-8");
		resp.setHeader("Content-Type", "application/json");
		HttpUtil httpUtil = HttpUtil.of(req);
		if (httpUtil == null) {
			JSONObject response = new JSONObject();
			response.put("status", false);
			response.put("message", "request invalid!");
			resp.getWriter().write(response.toJSONString());
		} else {
			IUserModel userModel = httpUtil.toModel(this.userModel.getClass());
			if(userModel.getIds() == null) {
				JSONObject response = new JSONObject();
				response.put("status", false);
				response.put("message", "list ID can not empty!");
				resp.getWriter().write(response.toJSONString());
			}
			userModel = userService.findAll(userModel.getIds());
			if (userModel == null) {
				JSONObject response = new JSONObject();
				response.put("status", false);
				response.put("message", "user or password not invalid!");
				resp.getWriter().write(response.toJSONString());
			} else {
				httpUtil.writeValue(resp.getOutputStream(), userModel);
			}
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setStatus(200);
		resp.setCharacterEncoding("UTF-8");
		resp.setHeader("Content-Type", "application/json");
		HttpUtil httpUtil = HttpUtil.of(req);
		if (httpUtil == null) {
			JSONObject response = new JSONObject();
			response.put("status", false);
			response.put("message", "request invalid!");
			resp.getWriter().write(response.toJSONString());
		} else {
			IUserModel userModel = httpUtil.toModel(this.userModel.getClass());
			userModel = userService.findByUserNameAndPasswordAndStatus(userModel.getUsername(), userModel.getUserpass(),
					true);
			if (userModel == null) {
				JSONObject response = new JSONObject();
				response.put("status", false);
				response.put("message", "user or password not invalid!");
				resp.getWriter().write(response.toJSONString());
			} else {
				httpUtil.writeValue(resp.getOutputStream(), userModel);
			}
		}
	}
}
