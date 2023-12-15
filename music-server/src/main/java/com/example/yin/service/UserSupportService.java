package com.example.yin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.yin.common.BaseResponse;
import com.example.yin.model.domain.UserSupport;
import com.example.yin.model.request.UserSupportRequest;

/**
 * @author asus
 * @description 针对表【user_support】的数据库操作Service
 * @createDate 2022-06-11 16:06:28
 */
public interface UserSupportService extends IService<UserSupport> {

    BaseResponse isUserSupportComment(UserSupportRequest userSupportRequest);

    BaseResponse insertCommentSupport(UserSupportRequest userSupportRequest);

    BaseResponse deleteCommentSupport(UserSupportRequest userSupportRequest);
}
