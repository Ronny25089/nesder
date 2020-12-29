package com.nesder.handler;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nesder.service.AccountService;
import com.nesder.utils.JwtTokenUtils;
import com.nesder.vo.resp.ApiResponse;

public class JWTBasicAuthenticationFilter extends BasicAuthenticationFilter {

	public JWTBasicAuthenticationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Autowired
	public AccountService accountService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
		// 如果请求头中没有Authorization信息则直接放行了
		if (tokenHeader == null || !tokenHeader.startsWith(JwtTokenUtils.TOKEN_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}
		// 如果请求头中有token，则进行解析，并且设置认证信息
		try {
			SecurityContextHolder.getContext().setAuthentication(getAuthentication(tokenHeader));
		} catch (TokenIsExpiredException e) {
			// 返回json形式的错误信息
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=utf-8");
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			ApiResponse apiResponse = new ApiResponse();
			apiResponse.setMsg( "统一处理，原因：" + e.getMessage());
			apiResponse.setData(e.getClass());
			String reason = "统一处理，原因：" + e.getMessage();
			response.getWriter().write(new ObjectMapper().writeValueAsString(reason));
			response.getWriter().flush();
			return;
		} catch (TokenValidationException e) {
			// 返回json形式的错误信息
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=utf-8");
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			String reason = "统一处理，原因：" + e.getMessage();
			response.getWriter().write(new ObjectMapper().writeValueAsString(reason));
			response.getWriter().flush();
			return;
		}
		super.doFilterInternal(request, response, chain);
	}

	// 这里从token中获取用户信息并新建一个token
	private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader)
			throws TokenIsExpiredException, TokenValidationException {
		String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");
		boolean expiration = JwtTokenUtils.isExpiration(token);
		int id = JwtTokenUtils.getUserId(token);
		boolean noExsiting = accountService.findById(id) != null ? true : false;
		if (expiration) {
			throw new TokenIsExpiredException("token超时了");
		}
		if (noExsiting) {
			throw new TokenValidationException("token验证失败");
		} else {
			String username = JwtTokenUtils.getUsername(token);
			String role = JwtTokenUtils.getUserRole(token);
			if (username != null) {
				return new UsernamePasswordAuthenticationToken(username, null,
						Collections.singleton(new SimpleGrantedAuthority(role)));
			}
		}
		return null;
	}
}