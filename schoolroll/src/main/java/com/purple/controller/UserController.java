package com.purple.controller;

import com.purple.commons.RequestHolder;
import com.purple.entities.User;
import com.purple.service.UserService;
import com.purple.util.JsonData;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@CrossOrigin
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/users")
    public JsonData findAllByNoNot(Pageable pageable) {
        System.out.println(pageable);
        return JsonData.success(userService.findByNoNot(pageable));
    }

    @GetMapping("/user")
    public JsonData findByNoNotAndTypeNot () {
        return JsonData.success(userService.findByNoNotAndTypeNot());
    }
    @PostMapping("/user")
    public JsonData save (@RequestBody User user) {
        userService.save(user);
        return JsonData.success("添加成功");
    }
    @PutMapping("/user")
    public JsonData update (User user) {
        return JsonData.success("更新成功");
    }
    @DeleteMapping("/user")
    public JsonData delete (@RequestBody User user) {
        userService.deleteById(user.getId());
        return JsonData.success("删除成功");
    }

    @GetMapping("/userSpe")
    public JsonData getUserSpeciality() {
        User user = RequestHolder.getCurrentUser();
        User u = userService.findById(user.getId());
        return JsonData.success(u.getSpeciality());
    }
}
