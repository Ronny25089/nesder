package com.nesder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nesder.service.PostMarkService;
import com.nesder.vo.resp.ApiResponse;
import com.nesder.vo.resq.AddPostMark;

@RestController
@RequestMapping("/nesder/postmark")
public class PostMarkController {

	@Autowired
	private PostMarkService postMarkService;

	@GetMapping("/all")
	public ApiResponse listPostMark() {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(postMarkService.findAll());
		return apiResponse;
	}
	
	@PostMapping("/findaid")
	public ApiResponse findAidInfo(@RequestBody AddPostMark postMark1) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(postMarkService.findAid(postMark1));
		return apiResponse;
	}	
	
	@PostMapping("/finduid")
	public ApiResponse findUidInfo(@RequestBody AddPostMark postMark1) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(postMarkService.findUid(postMark1));
		return apiResponse;
	}	
	
	@PostMapping("/add")
	public ApiResponse addPostMark(@RequestBody AddPostMark postMark1) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(postMarkService.addAticleMarkInfo(postMark1));
		return apiResponse;
	}	
	
	@DeleteMapping("/delete/{aid}/{uid}")
	public ApiResponse delete(@PathVariable("aid" ) Integer aid,@PathVariable("uid" ) Integer uid) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(postMarkService.deletePostMark(aid, uid));
		return apiResponse;
	}
}
