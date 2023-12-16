package com.example.yin.controller.portal;

import com.example.yin.common.BaseResponse;
import com.example.yin.model.request.UserRequest;
import com.example.yin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     */
    @PostMapping("/user/add")
    public BaseResponse addUser(@RequestBody UserRequest registryRequest) {
        return userService.addUser(registryRequest);
    }

    /**
     * 登录判断
     */
    @PostMapping("/user/login/status")
    public BaseResponse loginStatus(@RequestBody UserRequest loginRequest, HttpSession session) {
        return userService.loginStatus(loginRequest, session);
    }

    /**
     * 返回指定 ID 的用户
     */
    @GetMapping("/user/detail")
    public BaseResponse userOfId(@RequestParam int id) {
        return userService.userOfId(id);
    }

    /**
     * 更新用户信息
     */
    @PostMapping("/user/update")
    public BaseResponse updateUserMsg(@RequestBody UserRequest updateRequest) {
        return userService.updateUserMsg(updateRequest);
    }

    /**
     * 更新用户密码
     */
    @PostMapping("/user/updatePassword")
    public BaseResponse updatePassword(@RequestBody UserRequest updatePasswordRequest) {
        return userService.updatePassword(updatePasswordRequest);
    }

    /**
     * 更新用户头像
     */
    @PostMapping("/user/avatar/update")
    public BaseResponse updateUserPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id") int id) {
        return userService.updateUserAvator(avatorFile, id);
    }
}
