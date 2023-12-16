package com.example.yin.controller.admin;

import com.example.yin.common.BaseResponse;
import com.example.yin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description 管理面用户控制器
 * @Author wendyma
 * @Date 2023/12/16 21:26
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin")
public class AdminUserController {

    @Autowired
    private UserService userService;

    /**
     * TODO 管理界面调用
     * 返回所有用户
     */
    @GetMapping("/user")
    public BaseResponse allUser() {
        return userService.allUser();
    }

    /**
     * TODO 管理界面的调用
     * 删除用户
     */
    @DeleteMapping("/user")
    public BaseResponse deleteUser(@RequestParam int id) {
        return userService.deleteUser(id);
    }

}
