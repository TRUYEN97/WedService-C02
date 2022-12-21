package com.tec02.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.tec02.model.request.IRequesterModel;
import com.tec02.utils.HttpUtil;
import com.tec02.utils.ResponseUtil;

public abstract class AbsWebservice extends HttpServlet {

	/**
	 * 
	 */
	@Inject
	private IRequesterModel requesterModel;

	private static final long serialVersionUID = 1L;

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpUtil.initResponse(resp);
		IRequesterModel requesterModel = HttpUtil.getRequestModel(req, resp, this.requesterModel.getClass());
		JSONObject response = null;
		try {
			if (requesterModel != null) {
				response = doDeleteAPI(req, requesterModel, resp);
			} else {
				response = createMessageRequestIsInvalid();
			}
		} catch (Exception e) {
			response = ResponseUtil.createMessager(false, null, e.getMessage());
		} finally {
			ResponseUtil.writeResponse(resp, response);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpUtil.initResponse(resp);
		IRequesterModel requesterModel = HttpUtil.getRequestModel(req, resp, this.requesterModel.getClass());
		JSONObject response = null;
		try {
			if (requesterModel != null) {
				response = doGetAPI(req, requesterModel, resp);
			} else {
				response = createMessageRequestIsInvalid();
			}
		} catch (Exception e) {
			response = ResponseUtil.createMessager(false, null, e.getMessage());
		} finally {
			ResponseUtil.writeResponse(resp, response);
		}
	}

	@Override
	protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpUtil.initResponse(resp);
		IRequesterModel requesterModel = HttpUtil.getRequestModel(req, resp, this.requesterModel.getClass());
		JSONObject response = null;
		try {
			if (requesterModel != null) {
				response = doHeadAPI(req, requesterModel, resp);
			} else {
				response = createMessageRequestIsInvalid();
			}
		} catch (Exception e) {
			response = ResponseUtil.createMessager(false, null, e.getMessage());
		} finally {
			ResponseUtil.writeResponse(resp, response);
		}
	}

	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpUtil.initResponse(resp);
		IRequesterModel requesterModel = HttpUtil.getRequestModel(req, resp, this.requesterModel.getClass());
		JSONObject response = null;
		try {
			if (requesterModel != null) {
				response = doOptionsAPI(req, requesterModel, resp);
			} else {
				response = createMessageRequestIsInvalid();
			}
		} catch (Exception e) {
			response = ResponseUtil.createMessager(false, null, e.getMessage());
		} finally {
			ResponseUtil.writeResponse(resp, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpUtil.initResponse(resp);
		IRequesterModel requesterModel = HttpUtil.getRequestModel(req, resp, this.requesterModel.getClass());
		JSONObject response = null;
		try {
			if (requesterModel != null) {
				response = doPostAPI(req, requesterModel, resp);
			} else {
				response = createMessageRequestIsInvalid();
			}
		} catch (Exception e) {
			response = ResponseUtil.createMessager(false, null, e.getMessage());
		} finally {
			ResponseUtil.writeResponse(resp, response);
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpUtil.initResponse(resp);
		IRequesterModel requesterModel = HttpUtil.getRequestModel(req, resp, this.requesterModel.getClass());
		JSONObject response = null;
		try {
			if (requesterModel != null) {
				response = doPutAPI(req, requesterModel, resp);
			} else {
				response = createMessageRequestIsInvalid();
			}
		} catch (Exception e) {
			response = ResponseUtil.createMessager(false, null, e.getMessage());
		} finally {
			ResponseUtil.writeResponse(resp, response);
		}
	}

	@Override
	protected void doTrace(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpUtil.initResponse(resp);
		IRequesterModel requesterModel = HttpUtil.getRequestModel(req, resp, this.requesterModel.getClass());
		JSONObject response = null;
		try {
			if (requesterModel != null) {
				response = doTraceAPI(req, requesterModel, resp);
			} else {
				response = createMessageRequestIsInvalid();
			}
		} catch (Exception e) {
			response = ResponseUtil.createMessager(false, null, e.getMessage());
		} finally {
			ResponseUtil.writeResponse(resp, response);
		}
	}

	protected JSONObject doGetAPI(HttpServletRequest req, IRequesterModel requesterModel, HttpServletResponse resp) {
		return createMessageRequestIsInvalid();
	}

	protected JSONObject doHeadAPI(HttpServletRequest req, IRequesterModel requesterModel, HttpServletResponse resp){
		return createMessageRequestIsInvalid();
	}

	protected JSONObject doOptionsAPI(HttpServletRequest req, IRequesterModel requesterModel, HttpServletResponse resp){
		return createMessageRequestIsInvalid();
	}

	protected JSONObject doPostAPI(HttpServletRequest req, IRequesterModel requesterModel, HttpServletResponse resp){
		return createMessageRequestIsInvalid();
	}

	protected JSONObject doPutAPI(HttpServletRequest req, IRequesterModel requesterModel, HttpServletResponse resp){
		return createMessageRequestIsInvalid();
	}

	protected JSONObject doTraceAPI(HttpServletRequest req, IRequesterModel requesterModel, HttpServletResponse resp){
		return createMessageRequestIsInvalid();
	}

	protected JSONObject doDeleteAPI(HttpServletRequest req, IRequesterModel requesterModel, HttpServletResponse resp){
		return createMessageRequestIsInvalid();
	}

	private JSONObject createMessageRequestIsInvalid() {
		return ResponseUtil.createMessager(false, null, "request is invalid!");
	}
	
}
