package com.example.yin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.yin.common.BaseResponse;
import com.example.yin.model.domain.Collect;
import com.example.yin.model.request.CollectRequest;

public interface CollectService extends IService<Collect> {

    BaseResponse addCollection(CollectRequest addCollectRequest);

    BaseResponse existSongId(CollectRequest isCollectRequest);

    BaseResponse deleteCollect(Integer userId, Integer songId);

    BaseResponse collectionOfUser(Integer userId);
}
