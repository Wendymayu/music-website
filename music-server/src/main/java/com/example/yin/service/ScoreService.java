package com.example.yin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.yin.common.BaseResponse;
import com.example.yin.model.domain.ScorePo;
import com.example.yin.model.request.ScoreRequest;

public interface ScoreService extends IService<ScorePo> {

    BaseResponse addRank(ScoreRequest rankListAddRequest);

    BaseResponse rankOfSongListId(Long songListId);

    BaseResponse getUserRank(Long consumerId, Long songListId);

}
