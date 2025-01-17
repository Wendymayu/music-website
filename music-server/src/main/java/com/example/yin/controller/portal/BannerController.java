package com.example.yin.controller.portal;

import com.example.yin.common.BaseResponse;
import com.example.yin.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页轮播图控制器
 */
@RestController
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @GetMapping("/getAllBanner")
    public BaseResponse getAllBanner() {
        return BaseResponse.success("成功获取轮播图与", bannerService.getAllBanner());
    }
}
