package com.example.yin.controller.portal;

import com.example.yin.common.BaseResponse;
import com.example.yin.model.request.CollectRequest;
import com.example.yin.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 收藏控制器
 */
@RestController
public class CollectController {

    @Autowired
    private CollectService collectService;

    // 添加收藏的歌曲
    @PostMapping("/collection/add")
    public BaseResponse addCollection(@RequestBody CollectRequest addCollectRequest) {
        return collectService.addCollection(addCollectRequest);
    }

    // 取消收藏的歌曲
    @DeleteMapping("/collection/delete")
    public BaseResponse deleteCollection(@RequestParam Integer userId, @RequestParam Integer songId) {
        return collectService.deleteCollect(userId, songId);
    }

    // 是否收藏了歌曲
    @PostMapping("/collection/status")
    public BaseResponse isCollection(@RequestBody CollectRequest isCollectRequest) {
        return collectService.existSongId(isCollectRequest);

    }

    // 返回的指定用户 ID 收藏的列表
    @GetMapping("/collection/detail")
    public BaseResponse collectionOfUser(@RequestParam Integer userId) {
        return collectService.collectionOfUser(userId);
    }
}
