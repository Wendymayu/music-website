package com.example.yin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.yin.common.BaseResponse;
import com.example.yin.model.domain.Consumer;
import com.example.yin.model.request.ConsumerRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

public interface ConsumerService extends IService<Consumer> {

    BaseResponse addUser(ConsumerRequest registryRequest);

    BaseResponse updateUserMsg(ConsumerRequest updateRequest);

    BaseResponse updateUserAvator(MultipartFile avatorFile, int id);

    BaseResponse updatePassword(ConsumerRequest updatePasswordRequest);

    boolean existUser(String username);

    boolean verityPasswd(String username, String password);

    BaseResponse deleteUser(Integer id);

    BaseResponse allUser();

    BaseResponse userOfId(Integer id);

    BaseResponse loginStatus(ConsumerRequest loginRequest, HttpSession session);

}
