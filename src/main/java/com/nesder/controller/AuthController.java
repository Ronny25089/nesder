package com.nesder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nesder.model.UserContext;
import com.nesder.service.AccountService;
import com.nesder.vo.resp.ApiResponse;
import com.nesder.vo.resq.RegistUser;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AccountService accountService;

	@PostMapping("/regist")
	public ApiResponse sign(@RequestBody RegistUser user) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(accountService.sign(user));
		return apiResponse;
	}
	
	@PostMapping("/login")
	public ApiResponse login(@RequestBody UserContext loginUser) {
		ApiResponse apiResponse = new ApiResponse();
		accountService.loadUserByUsername(loginUser.getUsername());
		return apiResponse;
	}
}
