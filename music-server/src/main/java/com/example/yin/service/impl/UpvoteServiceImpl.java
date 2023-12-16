package com.example.yin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.yin.common.BaseResponse;
import com.example.yin.mapper.UpvoteMapper;
import com.example.yin.model.domain.UpvotePo;
import com.example.yin.model.request.UpvoteRequest;
import com.example.yin.service.UpvoteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author asus
 * @description 针对表【user_support】的数据库操作Service实现
 * @createDate 2022-06-11 16:06:28
 */
@Service
public class UpvoteServiceImpl extends ServiceImpl<UpvoteMapper, UpvotePo>
        implements UpvoteService {

    @Autowired
    private UpvoteMapper upvoteMapper;

    @Override
    public BaseResponse isUserSupportComment(UpvoteRequest upvoteRequest) {
        QueryWrapper<UpvotePo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("comment_id", upvoteRequest.getCommentId());
        queryWrapper.eq("user_id", upvoteRequest.getUserId());
        if (upvoteMapper.selectCount(queryWrapper) > 0) {
            return BaseResponse.success("您已取消点赞", true);
        } else {
            return BaseResponse.success("您已点赞", false);
        }
    }

    @Override
    public BaseResponse insertCommentSupport(UpvoteRequest upvoteRequest) {
        UpvotePo upvotePo = new UpvotePo();
        BeanUtils.copyProperties(upvoteRequest, upvotePo);
        if (upvoteMapper.insert(upvotePo) > 0) {
            return BaseResponse.success("添加记录成功");
        }
        return BaseResponse.error("添加时,发生异常");
    }

    @Override
    public BaseResponse deleteCommentSupport(UpvoteRequest upvoteRequest) {
        QueryWrapper<UpvotePo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("comment_id", upvoteRequest.getCommentId());
        queryWrapper.eq("user_id", upvoteRequest.getUserId());
        if (upvoteMapper.delete(queryWrapper) > 0) {
            return BaseResponse.success("删除记录成功");
        }
        return BaseResponse.error("删除记录发生异常");
    }
}
