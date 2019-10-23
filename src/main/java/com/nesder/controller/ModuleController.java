package com.nesder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nesder.service.ModuleService;
import com.nesder.vo.resp.ApiResponse;
import com.nesder.vo.resq.AddModule;

@RestController
@RequestMapping("/nesder/module")
public class ModuleController {

	@Autowired
	private ModuleService moduleService;

	@GetMapping("/all")
	public ApiResponse listUser() {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(moduleService.findAll());
		return apiResponse;
	}

	@PostMapping("/add")
	public ApiResponse addModule(@RequestBody AddModule mode) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(moduleService.addModule(mode));
		return apiResponse;
	}

	@DeleteMapping("/delete/{id}")
	public ApiResponse delete(@PathVariable("id") Integer id) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(moduleService.deleteModule(id));
		return apiResponse;
	}
	
	@PostMapping("/update")
	public ApiResponse updateModule(@RequestBody AddModule mode) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(moduleService.updateModule(mode));
		return apiResponse;
	}
}
