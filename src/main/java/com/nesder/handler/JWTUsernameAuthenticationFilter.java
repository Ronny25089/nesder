package com.nesder.handler;	

import java.io.IOException;	
import java.util.ArrayList;	

import javax.servlet.FilterChain;	
import javax.servlet.ServletException;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	

import org.springframework.security.authentication.AuthenticationManager;	
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;	
import org.springframework.security.core.Authentication;	
import org.springframework.security.core.AuthenticationException;	
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;	

import com.fasterxml.jackson.databind.ObjectMapper;	
import com.nesder.model.UserContext;	
import com.nesder.utils.JwtTokenUtils;	
import com.nesder.vo.resq.LoginUser;	

public class JWTUsernameAuthenticationFilter extends UsernamePasswordAuthenticationFilter {	

	private AuthenticationManager authenticationManager;	

	public JWTUsernameAuthenticationFilter(AuthenticationManager authenticationManager) {	
		this.authenticationManager = authenticationManager;	
		super.setFilterProcessesUrl("/auth/login");	
	}	

	@Override	
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)	
			throws AuthenticationException {	

		// 获取到登录的信息	
		try {	
			LoginUser loginUser = new ObjectMapper().readValue(request.getInputStream(), LoginUser.class);	
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(),	
					loginUser.getPassword(), new ArrayList<>()));	
		} catch (IOException e) {	
			e.printStackTrace();	
			return null;	
		}	
	}	

	// 成功验证后调用的方法	
	// 如果验证成功，就生成token并返回	
	@Override	
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,	
			Authentication authentication) throws IOException, ServletException {	

		UserContext userContext = (UserContext) authentication.getPrincipal();	

		String accessToken = JwtTokenUtils.createToken(userContext);	
		// 返回创建成功的token	
		// 但是这里创建的token只是单纯的token	
		// 按照jwt的规定，最后请求的时候应该是 `Bearer token`	
		response.setHeader("token", JwtTokenUtils.TOKEN_PREFIX + accessToken);	
	}	

	@Override	
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,	
			AuthenticationException failed) throws IOException, ServletException {	
		response.setStatus(403);	
		response.getWriter().write("authentication failed, reason: " + failed.getMessage());	
	}	

}