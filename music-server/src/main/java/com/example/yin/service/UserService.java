package com.example.yin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.yin.common.BaseResponse;
import com.example.yin.model.domain.UserPo;
import com.example.yin.model.request.UserRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

public interface UserService extends IService<UserPo> {

    BaseResponse addUser(UserRequest registryRequest);

    BaseResponse updateUserMsg(UserRequest updateRequest);

    BaseResponse updateUserAvator(MultipartFile avatorFile, int id);

    BaseResponse updatePassword(UserRequest updatePasswordRequest);

    boolean existUser(String username);

    boolean verityPasswd(String username, String password);

    BaseResponse deleteUser(Integer id);

    BaseResponse allUser();

    BaseResponse userOfId(Integer id);

    BaseResponse loginStatus(UserRequest loginRequest, HttpSession session);

}
