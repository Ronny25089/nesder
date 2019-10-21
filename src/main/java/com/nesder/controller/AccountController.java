package com.nesder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nesder.service.AccountService;
import com.nesder.vo.resp.ApiResponse;

@RestController
@RequestMapping("/nesder/user")
public class AccountController {

	@Autowired
	private AccountService accountService;

//	@RequestMapping(value = "/accounts", method = RequestMethod.GET)
	@GetMapping("/all")
	public ApiResponse listUser() {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(accountService.findAll());
		return apiResponse;
	}

//	@RequestMapping(value = "/account/{id}", method = RequestMethod.DELETE)
	@DeleteMapping("/account/{id}")
	public ApiResponse delete(@PathVariable("id") Integer id) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(accountService.delete(id));
		return apiResponse;
	}
}
