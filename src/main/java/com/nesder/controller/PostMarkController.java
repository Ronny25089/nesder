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
@RequestMapping("/nesder/articlemark")
public class PostMarkController {

	@Autowired
	private PostMarkService articleMarkService;

	@GetMapping("/all")
	public ApiResponse listPostMark() {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(articleMarkService.findAll());
		return apiResponse;
	}
	
	@PostMapping("/findaid")
	public ApiResponse findAidInfo(@RequestBody AddPostMark articleMark1) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(articleMarkService.findAid(articleMark1));
		return apiResponse;
	}	
	
	@PostMapping("/finduid")
	public ApiResponse findUidInfo(@RequestBody AddPostMark articleMark1) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(articleMarkService.findUid(articleMark1));
		return apiResponse;
	}	
	
	@PostMapping("/add")
	public ApiResponse addPostMark(@RequestBody AddPostMark articleMark1) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(articleMarkService.addAticleMarkInfo(articleMark1));
		return apiResponse;
	}	
	
	@DeleteMapping("/delete/{aid}/{uid}")
	public ApiResponse delete(@PathVariable("aid" ) Integer aid,@PathVariable("uid" ) Integer uid) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(articleMarkService.deletePostMark(aid, uid));
		return apiResponse;
	}
}
