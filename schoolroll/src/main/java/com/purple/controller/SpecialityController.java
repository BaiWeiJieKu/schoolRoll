package com.purple.controller;

import com.purple.commons.RequestHolder;
import com.purple.entities.Speciality;
import com.purple.entities.User;
import com.purple.service.SpecialityService;
import com.purple.service.UserService;
import com.purple.util.JsonData;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin
public class SpecialityController {

    @Resource
    private SpecialityService specialityService;
    @Resource
    private UserService userService;

    @GetMapping("/speciality")
    public JsonData getSpeciality (Pageable pageable) {

        User user = RequestHolder.getCurrentUser();
        int type = user.getType();
        if (type == 3) {
            User userSpeciality = userService.findById(user.getId());
            return JsonData.success(userSpeciality.getSpeciality());
        } else {
            return JsonData.success(specialityService.findAll(pageable));
        }
    }

    @GetMapping("/speciality/{id}")
    public JsonData getSpeciality () {
        List<Speciality> specialities = specialityService.findAll();
        return JsonData.success(specialities);

    }

    @PostMapping("/speciality")
    public JsonData saveSpeciality (@RequestBody Speciality speciality) {
        User user = RequestHolder.getCurrentUser();
        int type = user.getType();
        specialityService.save(speciality, type);
        return JsonData.success("添加成功");
    }

    @PostMapping("/userSpeciality")
    public JsonData saveSpeciality (@RequestBody Integer[] specialities) {
        User user = RequestHolder.getCurrentUser();
        specialityService.update(user, specialities);
        return JsonData.success("更新成功");
    }

    @DeleteMapping("/speciality")
    public JsonData deleteSpeciality (@RequestBody Speciality speciality, HttpServletRequest request) {
        User user = RequestHolder.getCurrentUser();
        int type = user.getType();
        specialityService.delete(speciality.getId(), type);
        return JsonData.success("删除成功");
    }


}
