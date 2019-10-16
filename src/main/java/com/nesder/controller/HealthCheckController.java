package com.nesder.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nesder.vo.HealthModel;

@RestController
@RequestMapping("/test")
public class HealthCheckController {

	@RequestMapping("/hello")
	public HealthModel Hello() {
		HealthModel healModel = new HealthModel();
		healModel.setCode(200);
		healModel.setStatus("success");
		return healModel;
	}

	@RequestMapping("/te")
	public String test() {
		return "test";
	}
}

