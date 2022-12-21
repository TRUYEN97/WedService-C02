package com.tec02.controller.user;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tec02.controller.AbsWebservice;
import com.tec02.model.request.IRequesterModel;
import com.tec02.model.user.IUserModel;
import com.tec02.service.IUserService;
import com.tec02.utils.ResponseUtil;

@WebServlet(urlPatterns = {"/admin/api/v1/user"})
public class UserController extends AbsWebservice {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4695046210532522424L;

	@Inject
	private IUserService userService;

	@Inject
	private IUserModel userModel;

	private JSONObject search(HttpServletResponse resp, IRequesterModel requesterModel) throws JsonProcessingException {
		List<IUserModel> users;
		Object value = requesterModel.getValue();
		if (value == null || (users = userService.searchByName(value.toString())) == null) {
			return ResponseUtil.createMessager(false, null, "value is invalid!");
		} else {
			String strData = new ObjectMapper().writeValueAsString(users);
			return ResponseUtil.createMessager(true, JSONObject.parseArray(strData), "Ok");
		}
	}

	private JSONObject findAll(HttpServletResponse resp, IRequesterModel requesterModel)
			throws JsonProcessingException {
		long[] ids = requesterModel.getIds();
		if (ids == null) {
			return ResponseUtil.createMessager(false, null, "ids is invalid!");
		} else {
			List<IUserModel> users;
			if (ids.length == 0) {
				users = userService.findAll();
			} else {
				users = userService.findAll(ids);
			}
			String strData = new ObjectMapper().writeValueAsString(users);
			return ResponseUtil.createMessager(true, JSONObject.parseArray(strData), "Ok");
		}
	}

	private JSONObject findOne(HttpServletResponse resp, IRequesterModel requesterModel)
			throws JsonProcessingException {
		IUserModel user = null;
		long id = requesterModel.getId();
		if (id <= 0 || (user = userService.findOne(id)) == null) {
			return ResponseUtil.createMessager(false, null, "id is invalid!");
		} else {
			String strData = new ObjectMapper().writeValueAsString(user);
			return ResponseUtil.createMessager(true, JSONObject.parseObject(strData), "Ok");
		}
	}

	@Override
	protected JSONObject doPostAPI(HttpServletRequest req, IRequesterModel requesterModel, HttpServletResponse resp) {
		try {
			IUserModel userModel = requesterModel.getValue(this.userModel.getClass());
			String username ;
			String userpass ;
			if (userModel == null 
					|| (username = userModel.getUsername()) == null || username.isBlank() 
					|| (userpass = userModel.getUserpass()) == null || userpass.isBlank()) {
				return ResponseUtil.createMessager(false, null, "missing username or userpass");
			}
			if (!userService.searchByName(username).isEmpty()) {
				return ResponseUtil.createMessager(false, null, "username has exists!!");
			}
			if ((userModel = userService.addUser(userModel)) == null) {
				return ResponseUtil.createMessager(false, null, "add new user failed!");
			} else {
				String strData = new ObjectMapper().writeValueAsString(userModel);
				return ResponseUtil.createMessager(true, JSONObject.parseObject(strData), "Ok");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.createMessager(false, null,
					String.format("Error arises during data takes!\r\n%s",e.getMessage()));
		}
	}

	@Override
	protected JSONObject doGetAPI(HttpServletRequest req, IRequesterModel requesterModel, HttpServletResponse resp) {
		String action = requesterModel.getAction();
		if (action == null) {
			return ResponseUtil.createMessager(false, null, "action can not null!");
		}
		try {
			switch (action) {
			case "findAll":
				return findAll(resp, requesterModel);
			case "find":
				return findOne(resp, requesterModel);
			case "search":
				return search(resp, requesterModel);
			default:
				return ResponseUtil.createMessager(false, null, "action is invalid!");
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return ResponseUtil.createMessager(false, null,
					String.format("Error arises during data takes!\r\n%s",e.getMessage()));
		}
	}

}
