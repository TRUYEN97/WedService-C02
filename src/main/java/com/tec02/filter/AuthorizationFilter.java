package com.tec02.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.tec02.common.AllKeyword;
import com.tec02.utils.HttpUtil;
import com.tec02.utils.ResponseUtil;
import com.tec02.utils.TokenUtil;

import io.jsonwebtoken.Claims;

public class AuthorizationFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		String URI = httpServletRequest.getServletPath();
		if (!URI.toLowerCase().startsWith("/admin")) {
			chain.doFilter(request, response);
		} else {
			JSONObject responseData = ResponseUtil.createMessager(false, null, "no permission");
			HttpUtil.initResponse(httpServletResponse);
			String jwt = httpServletRequest.getHeader(AllKeyword.AUTHORIZATION);
			Claims claims;
			if (jwt != null
					&& (claims = TokenUtil.decodeJWT(jwt)) != null) {
				String subject = claims.getSubject();
				if(subject != null && subject.equals("1")) {
					chain.doFilter(httpServletRequest, httpServletResponse);
					return;
				}
			}
			ResponseUtil.writeResponse(httpServletResponse, responseData);
		}
	}

	@Override
	public void destroy() {}

}
