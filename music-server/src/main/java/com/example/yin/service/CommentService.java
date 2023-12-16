package com.example.yin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.yin.common.BaseResponse;
import com.example.yin.model.domain.CommentPo;
import com.example.yin.model.request.CommentRequest;

public interface CommentService extends IService<CommentPo> {

    BaseResponse addComment(CommentRequest addCommentRequest);

    BaseResponse updateCommentMsg(CommentRequest upCommentRequest);

    BaseResponse deleteComment(Integer id);

    BaseResponse commentOfSongId(Integer songId);

    BaseResponse commentOfSongListId(Integer songListId);

}
