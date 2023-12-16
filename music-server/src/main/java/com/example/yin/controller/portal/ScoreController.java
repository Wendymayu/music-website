package com.example.yin.controller.portal;

import com.example.yin.common.BaseResponse;
import com.example.yin.model.request.ScoreRequest;
import com.example.yin.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 评分控制器
 */
@RestController
public class ScoreController {

    @Autowired
    private ScoreService scoreService;


    // 提交评分
    @PostMapping("/rankList/add")
    public BaseResponse addRank(@RequestBody ScoreRequest rankListAddRequest) {
        return scoreService.addRank(rankListAddRequest);
    }

    // 获取指定歌单的评分
    @GetMapping("/rankList")
    public BaseResponse rankOfSongListId(@RequestParam Long songListId) {
        return scoreService.rankOfSongListId(songListId);
    }

    // 获取指定用户的歌单评分
    @GetMapping("/rankList/user")
    public BaseResponse getUserRank(@RequestParam Long consumerId, @RequestParam Long songListId) {
        return scoreService.getUserRank(consumerId, songListId);
    }
}
