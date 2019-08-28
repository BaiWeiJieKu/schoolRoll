package com.purple.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.purple.commons.RequestHolder;
import com.purple.entities.User;
import com.purple.service.UserService;
import com.purple.util.JsonData;

@RestController
@CrossOrigin
public class LoginController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public JsonData login(@RequestBody User u, HttpServletRequest request) {
        User user = userService.findByNoAndPassword(u.getNo(), u.getPassword());
        System.out.println("controller-->"+request.getSession().getId());
        if (user!=null) {
            request.getSession().setAttribute("user", user);
            return JsonData.success(200, "登录成功");
        } else {
            return JsonData.error(500, "账号或密码错误");
        }
    }

    @GetMapping("/exit")
    public JsonData exit(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return JsonData.success("200");
    }

    @PutMapping("/updatePwd")
    public JsonData updatePwd (@RequestBody User u, HttpServletRequest request) {

        User user = RequestHolder.getCurrentUser();
        userService.updatePwd(user.getId(), u.getPassword());
        request.getSession().removeAttribute("user");
        return JsonData.success(200);
    }

    @GetMapping("/isCheck")
    public JsonData getUser() {
        User user = RequestHolder.getCurrentUser();
        return JsonData.success(user);
    }
}
