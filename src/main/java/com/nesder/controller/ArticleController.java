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

import com.nesder.service.ArticleService;
import com.nesder.vo.resp.ApiResponse;
import com.nesder.vo.resq.AddArticle;

@RestController
@RequestMapping("/nesder/article")
public class ArticleController {

	@Autowired
	private ArticleService ArticleService;

	@GetMapping("/all")
	public ApiResponse listArticle() {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(ArticleService.findAll());
		return apiResponse;
	}
	
	@PostMapping("/add")
	public ApiResponse addArticle(@RequestBody AddArticle article1) {
		
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(ArticleService.addAticleInfo(article1));
		return apiResponse;
	}	

	@PutMapping("/update")
	public ApiResponse updateAticleInfo(@RequestBody AddArticle article1) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(ArticleService.updateAticleInfo(article1));
		return apiResponse;
	}
	
	@DeleteMapping("/delete/{id}")
	public ApiResponse delete(@PathVariable("id" ) Integer id) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(ArticleService.deleteArticle(id));
		return apiResponse;
	}
}
