package com.example.yin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.yin.common.BaseResponse;
import com.example.yin.mapper.CollectMapper;
import com.example.yin.model.domain.CollectPo;
import com.example.yin.model.request.CollectRequest;
import com.example.yin.service.CollectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectServiceImpl extends ServiceImpl<CollectMapper, CollectPo> implements CollectService {
    @Autowired
    private CollectMapper collectMapper;

    @Override
    public BaseResponse addCollection(CollectRequest addCollectRequest) {
        //作者用type来判断收藏的是歌还是歌单
        CollectPo collectPo = new CollectPo();
        BeanUtils.copyProperties(addCollectRequest, collectPo);
        if (collectMapper.insert(collectPo) > 0) {
            return BaseResponse.success("收藏成功", true);
        } else {
            return BaseResponse.error("收藏失败");
        }
    }

    @Override
    public BaseResponse existSongId(CollectRequest isCollectRequest) {
        QueryWrapper<CollectPo> queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",isCollectRequest.getUserId());
        queryWrapper.eq("song_id",isCollectRequest.getSongId());
        if (collectMapper.selectCount(queryWrapper) > 0) {
            return BaseResponse.success("已收藏", true);
        } else {
            return BaseResponse.success("未收藏", false);
        }
    }

    @Override
    public BaseResponse deleteCollect(Integer userId, Integer songId) {
        QueryWrapper<CollectPo> queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("song_id",songId);
        if (collectMapper.delete(queryWrapper) > 0) {
            return BaseResponse.success("取消收藏", false);
        } else {
            return BaseResponse.error("取消收藏失败");
        }
    }

    @Override
    public BaseResponse collectionOfUser(Integer userId) {
        QueryWrapper<CollectPo> queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",userId);
        return BaseResponse.success("用户收藏", collectMapper.selectList(queryWrapper));
    }
}
