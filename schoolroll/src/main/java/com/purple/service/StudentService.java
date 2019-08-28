package com.purple.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.purple.dao.SpecialityRepository;
import com.purple.dao.StudentRepository;
import com.purple.entities.Speciality;
import com.purple.entities.Student;
import com.purple.entities.User;
import com.purple.entities.UserDto;

@Service
public class StudentService {

    @Resource
    private StudentRepository studentRepository;
    @Resource
    private UserService userService;
    @Resource
    private SpecialityRepository specialityRepository;

    public Page<Student> findAllByNameLike (String name, Pageable pageable) {
        return studentRepository.findAllByNameLike(name, pageable);
    }

    /**
     * 查询该用户下所有学校树
     * @param user
     * @return
     */
    public List<UserDto> findUser(User user) {
        List<UserDto> users = userService.findUserByNoNotAndTypeGreaterThan(user);
        return users;
    }

    @Transactional
    public void save (Student student, Integer sid, Integer uid) {
        Speciality speciality = specialityRepository.findOneById(sid);
        User user = userService.findById(uid);
        student.setUser(user);
        student.setSpeciality(speciality);
        studentRepository.saveAndFlush(student);
    }

    /**
     * 	查询该账户下所有的学籍
     * @return
     */
    public Page<Student> getStudent(User user, Pageable pageable) {
        //TODO 先去查询市
        int type = user.getType();
        List<User> users = new ArrayList<>();
        // user 的id
        List<Integer> utypes = new ArrayList<>();
        if (type==1) {
            //TODO 省级
            users = userService.findUserByType(type+1);
        } else if (type == 2) {
            // TODO 市级
            users.add(user);
        } else if (type==0){
            //TODO 超级管理员
            users = userService.findUserByType(2);
        } else {
        	users.add(user);
        }
        /**
         * 	筛选所有的市
         */
        if (type==1) {
            List<User> list = new ArrayList<>();
            for (User u : users) {
                if (user.getId() == u.getParentId()) {
                    list.add(u);
                }
            }
            users.clear();
            users.addAll(list);
        }
        for (User u : users) {
            utypes.add(u.getId());
        }
        List<User> utypesList = new ArrayList<>();
        if (type!=3) {
        	utypesList = userService.findUserByParentIdIn(utypes);
        } else {
        	utypesList = new ArrayList<>();
        	utypesList.add(user);
        }
        return studentRepository.findAllByUserIn(utypesList, pageable);
    }

    public List<Speciality> getSpeciality(int id) {
        User user = userService.findById(id);
        List<Speciality> specialities = new ArrayList<>();
        specialities.addAll(user.getSpeciality());
        return specialities;
    }
    
    public Page<Student> getCheckStudent(User user, Pageable pageable) {
    //TODO 先去查询市
      int type = user.getType();
      List<User> users = new ArrayList<>();
      // user 的id
      List<Integer> utypes = new ArrayList<>();
      if (type!=3 && type<3) {
          if (type==1) {
              //TODO 省级
              users = userService.findUserByType(type+1);
          } else if (type == 2) {
              // TODO 市级
              users.add(user);
          } else {
              //TODO 超级管理员
              users = userService.findUserByType(2);
          }
      }
      /**
       * 	筛选所有的市
       */
     if (type==1) {
          List<User> list = new ArrayList<>();
          for (User u : users) {
              if (user.getId() == u.getParentId()) {
                  list.add(u);
              }
          }
          users.clear();
          users.addAll(list);
      }
      for (User u : users) {
          utypes.add(u.getId());
      }
      List<User> utypesList = userService.findUserByParentIdIn(utypes);
      int max = 2;
      switch (user.getType()) {
			case 0:
				max = 2;
				break;
			case 1: 
				max = 2;
				break;
			case 2:
				max = 1;
				break;
			}
      return studentRepository.findAllByUserInAndPassLessThanAndPassGreaterThanEqualAndIsStateAndOperatorNot(utypesList, max, 0, 0, 3, pageable); 
    }
    
    @Transactional
    public void passStudent (Integer[] ids, User user) {
    	List<Integer> list = Arrays.asList(ids);
    	int role = 1;
    	if (user.getType() == 0 || user.getType() == 1) {
    		role = 2;
    	}
    	List<Student> students = studentRepository.findAllByIdIn(list);
    	for (Student student : students) {
    		student.setPass(role);
    	}
    	for (Student student : students) {
    		student.setOperatorState(1);
    		if (student.getNewSpeciality()!=null) {
    			student.setSpeciality(student.getNewSpeciality());
    		}
    		studentRepository.saveAndFlush(student);
    	}
    }

    @Transactional
    public void npassStudent (Integer[] ids, User user) {
    	List<Integer> list = Arrays.asList(ids);
    	int role = -1;
    	if (user.getType() == 0 || user.getType() == 1) {
    		role = -2;
    	}
    	List<Student> students = studentRepository.findAllByIdIn(list);
    	for (Student student : students) {
    		student.setPass(role);
    		student.setOperatorState(1);
    		student.setStateTime(null);
    		student.setIsState(0);
    	}
    	for (Student student : students) {
    		studentRepository.saveAndFlush(student);
    	}
    }
    
    // 退学
    public void quit(int id) {
    	Student student = studentRepository.findStudentById(id);
    	student.setPass(0);
    	student.setOperatorState(0);
    	student.setOperator(2);
    	studentRepository.saveAndFlush(student);
    }
    
    // 换专业
		public void speciality(int id, int speciality) {
			Speciality spec = specialityRepository.findOneById(speciality);
			Student student = studentRepository.findStudentById(id);
			student.setNewSpeciality(spec);
			student.setPass(0);
			student.setOperator(1);
			student.setOperatorState(0);
			studentRepository.saveAndFlush(student);
		}
		
		// 休学
		public void hugh(int id) {
			Student student = studentRepository.findStudentById(id);
			if (student.getOperator()!=4) {
				student.setOperator(4);
			} else if (student.getOperator()==4) {
				student.setOperator(5);
			}
			student.setPass(0);
			student.setOperatorState(0);
			studentRepository.saveAndFlush(student);
		}

		public void state(int id) {
			Student student = studentRepository.findStudentById(id);
			student.setOperatorState(0);
			student.setOperator(3);
			student.setPass(0);
			student.setStateTime(new Date());
			student.setIsState(1);
			studentRepository.saveAndFlush(student);
		}
		
		public Page<Student> stateStudent (User user,Pageable pageable) {
			int type = user.getType();
      List<User> users = new ArrayList<>();
      // user 的id
      List<Integer> utypes = new ArrayList<>();
      if (type!=3 && type<3) {
          if (type==1) {
              //TODO 省级
              users = userService.findUserByType(type+1);
          } else if (type == 2) {
              // TODO 市级
              users.add(user);
          } else {
              //TODO 超级管理员
              users = userService.findUserByType(2);
          }
      }
      /**
       * 	筛选所有的市
       */
     if (type==1) {
          List<User> list = new ArrayList<>();
          for (User u : users) {
              if (user.getId() == u.getParentId()) {
                  list.add(u);
              }
          }
          users.clear();
          users.addAll(list);
      }
      for (User u : users) {
          utypes.add(u.getId());
      }
      List<User> utypesList = userService.findUserByParentIdIn(utypes);
      return studentRepository.findAllByUserInAndOperatorAndPassNot(utypesList, 3, 2, pageable);
		}
}
