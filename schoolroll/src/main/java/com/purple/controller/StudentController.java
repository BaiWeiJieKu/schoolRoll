package com.purple.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.purple.commons.RequestHolder;
import com.purple.entities.Speciality;
import com.purple.entities.Student;
import com.purple.entities.StudentParam;
import com.purple.entities.User;
import com.purple.entities.UserDto;
import com.purple.service.StudentService;
import com.purple.util.JsonData;

@RestController
public class StudentController {

    @Resource
    private StudentService studentService;

    @PostMapping("/student")
    public JsonData save (@RequestBody StudentParam studentParam) {
        Student student = Student.builder().card(studentParam.getCard()).gender(studentParam.getGender())
                .name(studentParam.getName()).createTime(new Date())
                .isState(0).stateTime(null).entranceTime(new Date())
                .operator(0).pass(0).operatorState(1).build();
        Integer userId = studentParam.getUser();
        userId = userId == 0 ? RequestHolder.getCurrentUser().getId() : userId;
        studentService.save(student, studentParam.getSpeciality(), studentParam.getUser() );
        return JsonData.success("添加成功");
    }


    /**
     *  	 查询一个学校下所有的专业
     * @return
     */
    @GetMapping("/uSpecialitys")
    public JsonData getSpeciality(@RequestParam("id") int id) {
        User user = RequestHolder.getCurrentUser();
        if (user.getType()==3) {
            id = user.getId();
        }
        List<Speciality> specialities = studentService.getSpeciality(id);
        return JsonData.success(specialities);
    }

    /**
     *	获取该用户下所有的学生
     * @param pageable
     * @return
     */
    @GetMapping("/students")
    public JsonData getStudent(Pageable pageable) {
        Page<Student> speciality = studentService.getStudent(RequestHolder.getCurrentUser(), pageable);
        return JsonData.success(speciality);
    }

    /**
     * 	退学接口
     * @param id
     * @return
     */
    @GetMapping("/quitStudent")
    public JsonData quit(int id) {
    		studentService.quit(id);
        return JsonData.success();
    }

    /**
     * 	休学和恢复入学接口
     * @param id
     * @return
     */
    @GetMapping("/hughStudent")
    public JsonData hugh(int id) {
    	studentService.hugh(id);
      return JsonData.success();
    }

    /**
     * 		转专业接口
     * @return
     */
    @GetMapping("/studentSpeciality")
    public JsonData speciality(@RequestParam("id")int id, @RequestParam("speciality") int speciality) {
    	studentService.speciality(id, speciality);
      return JsonData.success();
    }

    /**
     * 		毕业接口
     * @param ids
     * @return
     */
    @GetMapping("/state")
    public JsonData state(@RequestParam("id")int id) {
    	studentService.state(id);
      return JsonData.success();
    }

    @GetMapping("/susers")
    public JsonData getUsers() {
        List<UserDto> user = studentService.findUser(RequestHolder.getCurrentUser());
        return JsonData.success(user);
    }
    
    /**
     * 	查询所有需要审核的
     */
    @GetMapping("/checkStudent")
    public JsonData getCheckStudent (Pageable pageable) {
    	User user = RequestHolder.getCurrentUser();
    	return JsonData.success(studentService.getCheckStudent(user, pageable));
    }
    
    @GetMapping("/ps")
    public JsonData passStudent (@RequestParam("params")Integer[] ids) {
    	User user = RequestHolder.getCurrentUser();
    	studentService.passStudent(ids, user);
    	return JsonData.success();
    }
    @GetMapping("/nps")
    public JsonData npassStudent (@RequestParam("params")Integer[] ids) {
    	User user = RequestHolder.getCurrentUser();
    	studentService.npassStudent(ids, user);
    	return JsonData.success();
    }
    
    @GetMapping("/stateStudent")
    public JsonData stateStudent(Pageable pageable) {
    	User user = RequestHolder.getCurrentUser();
    	Page<Student> students = studentService.stateStudent(user, pageable);
    	return JsonData.success(students);
    }

}
