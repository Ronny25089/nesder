package com.nesder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nesder.service.ArticleMarkService;
import com.nesder.vo.resp.ApiResponse;
import com.nesder.vo.resq.AddArticle;
import com.nesder.vo.resq.AddArticleMark;

@RestController
@RequestMapping("/nesder/articlemark")
public class ArticleMarkController {

	@Autowired
	private ArticleMarkService articleMarkService;

	@GetMapping("/all")
	public ApiResponse listArticleMark() {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(articleMarkService.findAll());
		return apiResponse;
	}
	
	@PostMapping("/add")
	public ApiResponse addArticleMark(@RequestBody AddArticleMark articleMark1) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(articleMarkService.addAticleMarkInfo(articleMark1));
		return apiResponse;
	}	

	
	@DeleteMapping("/delete/{aid}/{uid}")
	public ApiResponse delete(@PathVariable("aid" ) Integer aid,@PathVariable("uid" ) Integer uid) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(articleMarkService.deleteArticleMark(aid, uid));
		return apiResponse;
	}
}
