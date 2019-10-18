package com.nesder.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nesder.vo.ApiResponse;

@RestController
@RequestMapping("/nesder/test")
public class HealthCheckController {

	@RequestMapping("/hello")
	public ApiResponse Hello() {
		ApiResponse apiResponse = new ApiResponse();
		return apiResponse;
	}

	@RequestMapping("/te")
	public String test() {
		return "test";
	}
}

