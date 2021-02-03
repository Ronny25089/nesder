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

import com.nesder.service.ChannelService;
import com.nesder.vo.resp.ApiResponse;
import com.nesder.vo.resq.AddChannel;

@RestController
@RequestMapping("/nesder/channel")
public class ChannelController {

	@Autowired
	private ChannelService channelService;

	@GetMapping("/get/home")
	public ApiResponse listChannel() {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(channelService.findAll());
		return apiResponse;
	}

	@GetMapping("/get/forum/{id}")
	public ApiResponse listUser(@PathVariable("id") Integer id) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(channelService.findByForumId(id));
		return apiResponse;
	}

	@PostMapping("/add")
	public ApiResponse addChannel(@RequestBody AddChannel channel1) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(channelService.addChannel(channel1));
		return apiResponse;
	}	

	@DeleteMapping("/delete/{id}")
	public ApiResponse deleteChannel(@PathVariable("id") Integer id) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(channelService.deleteChannel(id));
		return apiResponse;
	}
	
	@PutMapping("/update")
	public ApiResponse updateChannel(@RequestBody AddChannel channel1) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(channelService.updateChannel(channel1));
		return apiResponse;
	}
}
