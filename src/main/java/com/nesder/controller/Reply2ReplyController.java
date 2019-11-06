package com.nesder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nesder.service.Reply2ReplyService;
import com.nesder.vo.resp.ApiResponse;
import com.nesder.vo.resq.AddReply2Reply;

@RestController
@RequestMapping("/nesder/reply2reply")
public class Reply2ReplyController {

	@Autowired
	private Reply2ReplyService Reply2ReplyService;

	@GetMapping("/all")
	public ApiResponse listReply2Reply() {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(Reply2ReplyService.findAll());
		return apiResponse;
	}
	
	@PostMapping("/findaid")
	public ApiResponse findAidInfo(@RequestBody AddReply2Reply reply2eeply1) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(Reply2ReplyService.findAid(reply2eeply1));
		return apiResponse;
	}	
	
	@PostMapping("/finduid")
	public ApiResponse findUidInfo(@RequestBody AddReply2Reply reply2eeply1) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(Reply2ReplyService.findUid(reply2eeply1));
		return apiResponse;
	}
	
	@PostMapping("/findrid")
	public ApiResponse findRidInfo(@RequestBody AddReply2Reply reply2eeply1) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(Reply2ReplyService.findRid(reply2eeply1));
		return apiResponse;
	}
	
	
	@PostMapping("/add")
	public ApiResponse addReply2Reply(@RequestBody AddReply2Reply reply2eeply1) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(Reply2ReplyService.addReply2ReplyInfo(reply2eeply1));
		return apiResponse;
	}	
	
	@DeleteMapping("/delete/{aid}/{uid}/{rid}")
	public ApiResponse delete(@PathVariable("aid" ) Integer aid,@PathVariable("uid" ) Integer uid,@PathVariable("rid" ) Integer rid) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(Reply2ReplyService.deleteReply2Reply(aid, uid, rid));
		return apiResponse;
	}
}
