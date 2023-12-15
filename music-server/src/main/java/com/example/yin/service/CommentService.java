package com.example.yin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.yin.common.BaseResponse;
import com.example.yin.model.domain.Comment;
import com.example.yin.model.request.CommentRequest;

public interface CommentService extends IService<Comment> {

    BaseResponse addComment(CommentRequest addCommentRequest);

    BaseResponse updateCommentMsg(CommentRequest upCommentRequest);

    BaseResponse deleteComment(Integer id);

    BaseResponse commentOfSongId(Integer songId);

    BaseResponse commentOfSongListId(Integer songListId);

}
