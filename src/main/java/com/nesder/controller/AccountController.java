package com.nesder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nesder.dao.domain.Account;
import com.nesder.service.AccountService;
import com.nesder.vo.ApiResponse;

@RestController
@RequestMapping("/nesder/user")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
//	@RequestMapping(value = "/accounts", method = RequestMethod.GET)
    @GetMapping("/accounts")
	public ApiResponse listUser() {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(accountService.findAll());
		return apiResponse;
    }
	
//	@RequestMapping(value="/register",method=RequestMethod.POST)
	@PostMapping("/register")
    public ApiResponse sign(@RequestBody Account account) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(accountService.sign(account));
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
