package com.purple.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.purple.commons.RequestHolder;
import com.purple.entities.Menu;
import com.purple.entities.User;
import com.purple.service.MenuService;
import com.purple.util.JsonData;

@RestController
@CrossOrigin
public class MenuController {

    @Resource
    private MenuService menuService;

    @GetMapping("/menu")
    public JsonData findAll (Pageable pageable) {
        return JsonData.success(menuService.findAll(pageable));
    }
    @PostMapping("/menu")
    public JsonData save (Menu menu) {
        menuService.save(menu);
        return JsonData.success("添加成功");
    }
    @DeleteMapping("/menu")
    public JsonData delteById (@RequestParam("id")Integer id) {
        menuService.deleteById(id);
        return JsonData.success("删除成功");
    }
    @GetMapping("/findByType")
    public JsonData findByType() {
        User user = RequestHolder.getCurrentUser();
        System.out.println("user:"+user);
        List<Menu> menus = menuService.findByType(user.getType());
        return JsonData.success(menus);
    }

}
