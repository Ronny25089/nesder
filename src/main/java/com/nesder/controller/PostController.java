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

import com.nesder.service.PostService;
import com.nesder.vo.resp.ApiResponse;
import com.nesder.vo.resq.AddPost;

@RestController
@RequestMapping("/nesder/post")
public class PostController {

	@Autowired
	private PostService PostService;

	@GetMapping("/all")
	public ApiResponse listPost() {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(PostService.findAll());
		return apiResponse;
	}
	
	@PostMapping("/add")
	public ApiResponse addPost(@RequestBody AddPost post1) {
		
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(PostService.addAticleInfo(post1));
		return apiResponse;
	}	

	@PutMapping("/update")
	public ApiResponse updateAticleInfo(@RequestBody AddPost post1) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(PostService.updateAticleInfo(post1));
		return apiResponse;
	}
	
	@DeleteMapping("/delete/{id}")
	public ApiResponse delete(@PathVariable("id" ) Integer id) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(PostService.deletePost(id));
		return apiResponse;
	}
}
