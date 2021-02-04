package com.nesder.controller;

import com.nesder.service.ChannelService;
import com.nesder.service.DetailsService;
import com.nesder.vo.resp.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nesder/details")
public class DetailsController {

    @Autowired
    private DetailsService detailsService;

    @GetMapping("/get/{id}")
    public ApiResponse getPostByChannelId(@PathVariable("id") Integer id) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(detailsService.findByChannelId(id));
        return apiResponse;
    }

    @GetMapping("/getPostDetails/{id}")
    public ApiResponse getPostDetailsByPostlId(@PathVariable("id") Integer id) throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(detailsService.findDetailsByPostId(id));
        return apiResponse;
    }
}
