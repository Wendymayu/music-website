package com.example.yin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.yin.common.BaseResponse;
import com.example.yin.mapper.CommentMapper;
import com.example.yin.model.domain.CommentPo;
import com.example.yin.model.request.CommentRequest;
import com.example.yin.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, CommentPo> implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public BaseResponse addComment(CommentRequest addCommentRequest) {
        CommentPo commentPo = new CommentPo();
        BeanUtils.copyProperties(addCommentRequest, commentPo);
        commentPo.setType(addCommentRequest.getNowType());
        if (commentMapper.insert(commentPo) > 0) {
            return BaseResponse.success("评论成功");
        } else {
            return BaseResponse.error("评论失败");
        }
    }

    @Override
    public BaseResponse updateCommentMsg(CommentRequest addCommentRequest) {
        CommentPo commentPo = new CommentPo();
        BeanUtils.copyProperties(addCommentRequest, commentPo);
        if (commentMapper.updateById(commentPo) > 0) {
            return BaseResponse.success("点赞成功");
        } else {
            return BaseResponse.error("点赞失败");
        }
    }

    //    删除评论
    @Override
    public BaseResponse deleteComment(Integer id) {
        if (commentMapper.deleteById(id) > 0) {
            return BaseResponse.success("删除成功");
        } else {
            return BaseResponse.error("删除失败");
        }
    }

    @Override
    public BaseResponse commentOfSongId(Integer songId) {
        QueryWrapper<CommentPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("song_id",songId);
        return BaseResponse.success(null, commentMapper.selectList(queryWrapper));
    }

    @Override
    public BaseResponse commentOfSongListId(Integer songListId) {
        QueryWrapper<CommentPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("song_list_id",songListId);
        return BaseResponse.success(null, commentMapper.selectList(queryWrapper));
    }
}
