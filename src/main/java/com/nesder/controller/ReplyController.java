package com.nesder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nesder.service.ReplyService;
import com.nesder.vo.resp.ApiResponse;
import com.nesder.vo.resq.AddReply;

@RestController
@RequestMapping("/nesder/reply")
public class ReplyController {

	@Autowired
	private ReplyService ReplyService;

	@GetMapping("/all")
	public ApiResponse listReply() {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(ReplyService.findAll());
		return apiResponse;
	}
	
	@PostMapping("/findaid")
	public ApiResponse findAidInfo(@RequestBody AddReply reply1) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(ReplyService.findPid(reply1));
		return apiResponse;
	}	
	
	@PostMapping("/finduid")
	public ApiResponse findUidInfo(@RequestBody AddReply reply1) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(ReplyService.findUid(reply1));
		return apiResponse;
	}
	
	
	@PostMapping("/add")
	public ApiResponse addReply(@RequestBody AddReply reply1) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(ReplyService.addReplyInfo(reply1));
		return apiResponse;
	}	
	
	@DeleteMapping("/delete/{aid}/{uid}")
	public ApiResponse delete(@PathVariable("aid" ) Integer aid,@PathVariable("uid" ) Integer uid) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(ReplyService.deleteReply(aid,uid));
		return apiResponse;
	}
}
