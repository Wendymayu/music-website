package com.example.yin.controller.portal;

import com.example.yin.common.BaseResponse;
import com.example.yin.model.request.CommentRequest;
import com.example.yin.model.request.UpvoteRequest;
import com.example.yin.service.CommentService;
import com.example.yin.service.UpvoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description 点赞控制器
 * @Author wendyma
 * @Date 2023/12/16 21:59
 * @Version 1.0
 */
@RestController
@RequestMapping("/upvote")
public class UpvoteController {
    @Resource
    private CommentService commentService;

    @Autowired
    private UpvoteService upvoteService;

    // 点赞
    @PostMapping("/comment/like")
    public BaseResponse commentOfLike(@RequestBody CommentRequest upCommentRequest) {
        return commentService.updateCommentMsg(upCommentRequest);
    }

    @PostMapping("/test")
    public BaseResponse isUserSupportComment(@RequestBody UpvoteRequest upvoteRequest) {
        return upvoteService.isUserSupportComment(upvoteRequest);
    }

    @PostMapping("/insert")
    public BaseResponse insertCommentSupport(@RequestBody UpvoteRequest upvoteRequest) {
        return upvoteService.insertCommentSupport(upvoteRequest);
    }

    @PostMapping("/delete")
    public BaseResponse deleteCommentSupport(@RequestBody UpvoteRequest upvoteRequest) {
        return upvoteService.deleteCommentSupport(upvoteRequest);
    }
}
