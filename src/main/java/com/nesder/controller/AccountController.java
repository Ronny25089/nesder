package com.nesder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nesder.service.AccountService;
import com.nesder.vo.resp.ApiResponse;
import com.nesder.vo.resq.RegistUser;

@RestController
@RequestMapping("/nesder/account")
public class AccountController {

	@Autowired
	private AccountService accountService;

//	@RequestMapping(value = "/accounts", method = RequestMethod.GET)
	@GetMapping("/all")
	public ApiResponse listUser() {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(accountService.findAll());
		apiResponse.setMsg("这是一个响应");
		return apiResponse;
	}
	@PostMapping("/add")
	public ApiResponse addAccount(@RequestBody RegistUser accountRegistUser) {
		return accountService.applications(accountRegistUser);
	}	

	@PostMapping("/sign")
	public ApiResponse signAccount(@RequestBody RegistUser accountRegistUser) {
		return accountService.signAccount(accountRegistUser);
	}	
	
	@DeleteMapping("/{id}")
	public ApiResponse delete(@PathVariable("id") Integer id) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(accountService.delete(id));
		return apiResponse;
	}
	
	@PutMapping("/update")
	public ApiResponse updateForum(@RequestBody RegistUser model) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(accountService.updateAccount(model));
		return apiResponse;
	}
}
