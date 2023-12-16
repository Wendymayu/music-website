package com.example.yin.controller.portal;

import com.example.yin.common.BaseResponse;
import com.example.yin.model.request.CommentRequest;
import com.example.yin.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 评论控制器
 */
@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    // 提交评论
    @PostMapping("/comment/add")
    public BaseResponse addComment(@RequestBody CommentRequest addCommentRequest) {
        return commentService.addComment(addCommentRequest);
    }

    // 删除评论
    @GetMapping("/comment/delete")
    public BaseResponse deleteComment(@RequestParam Integer id) {
        return commentService.deleteComment(id);
    }

    // 获得指定歌曲 ID 的评论列表
    @GetMapping("/comment/song/detail")
    public BaseResponse commentOfSongId(@RequestParam Integer songId) {
        return commentService.commentOfSongId(songId);
    }

    // 获得指定歌单 ID 的评论列表
    @GetMapping("/comment/songList/detail")
    public BaseResponse commentOfSongListId(@RequestParam Integer songListId) {
        return commentService.commentOfSongListId(songListId);
    }
}
