package com.nesder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nesder.service.AccountService;
import com.nesder.vo.resp.ApiResponse;
import com.nesder.vo.resq.RegistUser;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AccountService accountService;

//	@RequestMapping(value="/register",method=RequestMethod.POST)
	@PostMapping("/regist")
	public ApiResponse sign(@RequestBody RegistUser user) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(accountService.sign(user));
		return apiResponse;
	}
}
