package com.nesder.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nesder.utils.PropertiesUtils;
import com.nesder.vo.resp.ApiResponse;

@RestControllerAdvice
public class ApiExceptionHandler {

	private static final Logger LOG = LoggerFactory.getLogger(ApiExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ApiResponse handleException(Exception exception) {
		LOG.error("ApiExceptionHandler", exception);

		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMsg(PropertiesUtils.getEnvConfig("dbFailed"));
		apiResponse.setData(exception.getClass());
		return apiResponse;
	}
}