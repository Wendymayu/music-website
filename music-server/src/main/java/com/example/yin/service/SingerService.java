package com.example.yin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.yin.common.BaseResponse;
import com.example.yin.model.domain.SingerPo;
import com.example.yin.model.request.SingerRequest;
import org.springframework.web.multipart.MultipartFile;

public interface SingerService extends IService<SingerPo> {

    BaseResponse addSinger (SingerRequest addSingerRequest);

    BaseResponse updateSingerMsg(SingerRequest updateSingerRequest);

    BaseResponse updateSingerPic(MultipartFile avatorFile, int id);

    BaseResponse deleteSinger(Integer id);

    BaseResponse allSinger();

    BaseResponse singerOfName(String name);

    BaseResponse singerOfSex(Integer sex);
}
