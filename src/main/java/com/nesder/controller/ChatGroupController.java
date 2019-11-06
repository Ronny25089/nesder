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

import com.nesder.service.ChatGroupService;
import com.nesder.vo.resp.ApiResponse;
import com.nesder.vo.resq.AddChatGroup;

@RestController
@RequestMapping("/nesder/chatgroup")
public class ChatGroupController {

	@Autowired
	private ChatGroupService ChatGroupService;

	@GetMapping("/all")
	public ApiResponse listUser() {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(ChatGroupService.findAll());
		return apiResponse;
	}

	@PostMapping("/add")
	public ApiResponse addChatGroupInfo(@RequestBody AddChatGroup chatgroup1) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(ChatGroupService.addChatGroup(chatgroup1));
		return apiResponse;
	}

	@DeleteMapping("/delete/{id}")
	public ApiResponse delete(@PathVariable("id") Integer id) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(ChatGroupService.deleteChatGroup(id));
		return apiResponse;
	}
	
	@PutMapping("/update")
	public ApiResponse updateChatGroupInfo(@RequestBody AddChatGroup chatgroup1) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(ChatGroupService.updateChatGroup(chatgroup1));
		return apiResponse;
	}
}
