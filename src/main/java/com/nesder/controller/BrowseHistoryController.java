package com.nesder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nesder.service.BrowseHistoryService;
import com.nesder.vo.resp.ApiResponse;
import com.nesder.vo.resq.AddBrowseHistory;

@RestController
@RequestMapping("/nesder/browsehistory")
public class BrowseHistoryController {

	@Autowired
	private BrowseHistoryService BrowseHistoryService;

	@GetMapping("/all")
	public ApiResponse listBrowseHistory() {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(BrowseHistoryService.findAll());
		return apiResponse;
	}
	
	@PostMapping("/findaid")
	public ApiResponse findPidInfo(@RequestBody AddBrowseHistory browsehistory1) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(BrowseHistoryService.findPid(browsehistory1));
		return apiResponse;
	}	
	
	@PostMapping("/finduid")
	public ApiResponse findUidInfo(@RequestBody AddBrowseHistory browsehistory1) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(BrowseHistoryService.findUid(browsehistory1));
		return apiResponse;
	}
	
	
	@PostMapping("/add")
	public ApiResponse addReply(@RequestBody AddBrowseHistory browsehistory1) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(BrowseHistoryService.addBrowseHistoryInfo(browsehistory1));
		return apiResponse;
	}	
	
	@DeleteMapping("/delete/{aid}/{uid}")
	public ApiResponse delete(@PathVariable("aid" ) Integer aid,@PathVariable("uid" ) Integer uid) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(BrowseHistoryService.deleteReply(aid, uid));
		return apiResponse;
	}
}
