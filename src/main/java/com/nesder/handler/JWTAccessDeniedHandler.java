package com.nesder.handler;	

import java.io.IOException;	

import javax.servlet.ServletException;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	

import org.springframework.security.access.AccessDeniedException;	
import org.springframework.security.web.access.AccessDeniedHandler;	

import com.fasterxml.jackson.databind.ObjectMapper;	

/**	
 * @description:没有访问权限	
 *	
 */	
public class JWTAccessDeniedHandler implements AccessDeniedHandler {	
	@Override	
	public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,	
			AccessDeniedException e) throws IOException, ServletException {	
		httpServletResponse.setCharacterEncoding("UTF-8");	
		httpServletResponse.setContentType("application/json; charset=utf-8");	
		httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);	
		String reason = "统一处理，原因：" + e.getMessage();	
		httpServletResponse.getWriter().write(new ObjectMapper().writeValueAsString(reason));	
	}	
}