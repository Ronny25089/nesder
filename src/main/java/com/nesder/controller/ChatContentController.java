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

import com.nesder.service.ChatContentService;
import com.nesder.vo.resp.ApiResponse;
import com.nesder.vo.resq.AddChatContent;

@RestController
@RequestMapping("/nesder/chatcontent")
public class ChatContentController {

	@Autowired
	private ChatContentService ChatContentService;

	@GetMapping("/all")
	public ApiResponse listUser() {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(ChatContentService.findAll());
		return apiResponse;
	}

	@PostMapping("/add")
	public ApiResponse addChatContentInfo(@RequestBody AddChatContent chatcontent1) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(ChatContentService.addChatContent(chatcontent1));
		return apiResponse;
	}

	@DeleteMapping("/delete/{id}")
	public ApiResponse delete(@PathVariable("id") Integer id) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(ChatContentService.deleteChatContent(id));
		return apiResponse;
	}
	
	@PutMapping("/update")
	public ApiResponse updateChatContentInfo(@RequestBody AddChatContent chatcontent1) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(ChatContentService.updateChatContent(chatcontent1));
		return apiResponse;
	}
}
