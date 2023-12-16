package com.example.yin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.yin.common.BaseResponse;
import com.example.yin.mapper.ScoreMapper;
import com.example.yin.model.domain.ScorePo;
import com.example.yin.model.request.ScoreRequest;
import com.example.yin.service.ScoreService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 */
@Service
public class RankListServiceImpl extends ServiceImpl<ScoreMapper, ScorePo> implements ScoreService {


    @Autowired
    private ScoreMapper rankMapper;

    @Override
    public BaseResponse addRank(ScoreRequest rankListAddRequest) {
        ScorePo scorePo = new ScorePo();
        BeanUtils.copyProperties(rankListAddRequest, scorePo);
        if (rankMapper.insert(scorePo) > 0) {
            return BaseResponse.success("评价成功");
        } else {
            return BaseResponse.error("评价失败");
        }
    }

    @Override
    public BaseResponse rankOfSongListId(Long songListId) {
        // 评分总人数如果为 0，则返回0；否则返回计算出的结果
        QueryWrapper<ScorePo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("song_list_id",songListId);
        Long rankNum = rankMapper.selectCount(queryWrapper);
        return BaseResponse.success(null, (rankNum <= 0) ? 0 : rankMapper.selectScoreSum(songListId) / rankNum);
    }

    @Override
    public BaseResponse getUserRank(Long consumerId, Long songListId) {
        return BaseResponse.success(null, rankMapper.selectUserRank(consumerId, songListId));
    }
}
