package com.example.yin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.yin.common.BaseResponse;
import com.example.yin.model.domain.RankList;
import com.example.yin.model.request.RankListRequest;

public interface RankListService extends IService<RankList> {

    BaseResponse addRank(RankListRequest rankListAddRequest);

    BaseResponse rankOfSongListId(Long songListId);

    BaseResponse getUserRank(Long consumerId, Long songListId);

}
