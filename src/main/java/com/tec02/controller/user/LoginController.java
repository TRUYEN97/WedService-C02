package com.tec02.controller.user;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.tec02.controller.AbsWebservice;
import com.tec02.model.request.IRequesterModel;
import com.tec02.model.role.IRoleModel;
import com.tec02.model.user.IUserModel;
import com.tec02.service.IRoleService;
import com.tec02.service.IUserService;
import com.tec02.utils.ResponseUtil;
import com.tec02.utils.TokenUtil;

@WebServlet(urlPatterns = { "/api/v1/login" })
public class LoginController extends AbsWebservice {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8061016595360454573L;

	@Inject
	private IUserService userService;

	@Inject
	private IUserModel userModel;
	
	@Inject
	private IRoleService roleService;

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
			IUserModel user = userService.findByUserNameAndPasswordAndStatus(username, userpass, true);
			if (user == null) {
				return ResponseUtil.createMessager(false, null, "user or password not invalid!");
			} else {
				IRoleModel roleModel = this.roleService.getByID(user.getRole_id());
				String jwt = TokenUtil.createJWT(userModel, roleModel);
				if (jwt == null) {
					throw new Exception("created token failed!");
				}
				return ResponseUtil.createMessager(true, jwt, "Ok");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.createMessager(false, null,
					String.format("Error arises during data takes! %s", e.getMessage()));
		}

	}
}
