package com.nesder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nesder.service.FllowaccountService;
import com.nesder.vo.resp.ApiResponse;
import com.nesder.vo.resq.AddFllowaccount;

@RestController
@RequestMapping("/nesder/fllowaccount")
public class FllowaccountController {

	@Autowired
	private FllowaccountService FllowaccountService;

	@GetMapping("/all")
	public ApiResponse listFllowaccount() {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(FllowaccountService.findAll());
		return apiResponse;
	}
	
	@PostMapping("/fllowerid")
	public ApiResponse listFllowaccountFllowerInfo(@RequestBody AddFllowaccount fllowAccount1){
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(FllowaccountService.findFllowerId(fllowAccount1));
		return apiResponse;
	}

	@PostMapping("/fllowedid")
	public ApiResponse listFllowaccountFllowedInfo(@RequestBody AddFllowaccount fllowAccount1){
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(FllowaccountService.findFllowedId(fllowAccount1));
		return apiResponse;
	}
	
	@PostMapping("/add")
	public ApiResponse addfllowaccount(@RequestBody AddFllowaccount fllowAccount1) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(FllowaccountService.addfllowAccount(fllowAccount1));
		return apiResponse;
	}	

	@DeleteMapping("/delete/{fllowd_id}/{fllower_id}")
	public ApiResponse delete(@PathVariable("fllowd_id") Integer fllowd_id, @PathVariable("fllower_id") Integer fllower_id) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(FllowaccountService.deletefllowAccount(fllowd_id,fllower_id));
		return apiResponse;
	}
}
