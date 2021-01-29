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

import com.nesder.service.ForumService;
import com.nesder.vo.resp.ApiResponse;
import com.nesder.vo.resq.AddForum;

@RestController
@RequestMapping("/nesder")
public class ForumController {

	@Autowired
	private ForumService forumService;

	@GetMapping("/forum/all")
	public ApiResponse listUser() {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(forumService.findAll());
		return apiResponse;
	}
	
	@GetMapping("/forum")
	public ApiResponse getForum(@PathVariable("id") Integer id) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(forumService.getById(id));
		return apiResponse;
	}

	@PostMapping("/forum")
	public ApiResponse addForum(@RequestBody AddForum mode) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(forumService.addForum(mode));
		return apiResponse;
	}

	@DeleteMapping("/forum/{id}")
	public ApiResponse delete(@PathVariable("id") Integer id) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(forumService.deleteForum(id));
		return apiResponse;
	}
	
	@PutMapping("/forum")
	public ApiResponse updateForum(@RequestBody AddForum mode) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(forumService.updateForum(mode));
		return apiResponse;
	}
}
