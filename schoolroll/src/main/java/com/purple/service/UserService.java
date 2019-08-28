package com.purple.service;

import com.purple.dao.SpecialityRepository;
import com.purple.entities.UserDto;
import com.purple.dao.UserRepository;
import com.purple.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class UserService {

    @Resource
    private UserRepository userRepository;
    @Resource
    private SpecialityRepository specialityRepository;

    public User findByNo(String no) {
        return userRepository.findUserByNo(no);
    }

    public User findById(Integer id) {
        return userRepository.findUserById(id);
    }

    public User findByNoAndPassword(String no, String password) {
        return userRepository.findUserByNoAndPassword(no, password);
    }

    public List<UserDto> findUserByNoNotAndTypeGreaterThan(User puser) {
        Integer type = puser.getType();
        List<User> users = userRepository.findUserByNoNotAndTypeGreaterThan("admin", type);
        if (type !=0) {
            List<User> temp = new ArrayList<>();
            for (User u : users) {
                if (u.getParentId() == puser.getId() || u.getType()==3) {
                    temp.add(u);
                }
            }
            users.clear();
            users.addAll(temp);
        }
        type++;
        List<User> rootUser = new ArrayList<User>();
        List<User> userStudent = new ArrayList<>();
        for( int i=0; i<users.size(); i++) {
            User user = users.get(i);
            if (user.getType() == type) {
                rootUser.add(user);
                users.remove(i);
                i--;
            }
        }
        for( int i=0; i<users.size(); i++) {
            User user = users.get(i);
            if (user.getType()==3) {
                userStudent.add(user);
                users.remove(i);
                i--;
            }
        }

        List<UserDto> userDtos = null;
        if (type-1==0) {
            userDtos = adptUserDtoList(rootUser, users, userStudent);
        }
        else if(type-1==1) {
            userDtos = adptUserDtoList(rootUser, userStudent);
        } else {
            userDtos = new ArrayList<>();
            for (User user : rootUser) {
                userDtos.add(UserDto.transform(user));
            }
        }
        return userDtos;
    }

    public List<UserDto> adptUserDtoList(List<User> rootUsers, List<User> users, List<User> userStudent) {
        List<UserDto> userDtos = new ArrayList<>();
        for (User u : rootUsers) {
            UserDto userDto = UserDto.transform(u);
            userDtos.add(userDto);
        }
        List<UserDto> temp = adptUserDtoList(users, userStudent);
        for (UserDto ud : userDtos) {
            List<User> children = new ArrayList<>();
            for (int i=0; i<temp.size(); i++) {
                if (temp.get(i).getParentId() ==  ud.getId()) {
                    children.add(temp.get(i));
                    temp.remove(i);
                    i--;
                }
            }
            ud.setChildren(children);
        }
        return userDtos;
    }

    public Page<User> findByNoNot(Pageable pageable) {
        return userRepository.findAllByNoNot("admin", pageable);
    }

    /**
     * 查询所有学校的树
     * @return
     */
    public List<UserDto> findByNoNotAndTypeNot() {
        List<User> userList = userRepository.findAllByNoNotAndTypeNot("admin", 3);
        List<User> rootList = new ArrayList<>();
        for (int i=0; i<userList.size(); i++) {
            if (userList.get(i).getType() == 1) {
                rootList.add(userList.get(i));
                userList.remove(i);
                i--;
            }
        }
        return adptUserDtoList(rootList, userList);
    }
    public void save (User user) {
        user.setCreateTime(new Date());
        userRepository.save(user);
    }
    public List<User> findUserByType(Integer type) {
        return userRepository.findUserByType(type);
    }

    /**
     * 查询符合条件的学校
     * @param parentIds
     * @return
     */
    public List<User> findUserByParentIdIn(Collection<Integer> parentIds) {
        return userRepository.findAllByParentIdIn(parentIds);
    }
    public void deleteById (Integer id) {
        userRepository.deleteById(id);
    }
    public void updatePwd (Integer id, String password) {
        User user = userRepository.findUserById(id);
        user.setPassword(password);
        userRepository.save(user);
    }


    public List<UserDto> adptUserDtoList (List<User> rootList, List<User> userList) {
        List<UserDto> userDtoList = new ArrayList<>();
        for (int i=0; i<rootList.size(); i++) {
            User user = rootList.get(i);
            List<User> tempList = new ArrayList<>();
            for (int j=0; j<userList.size(); j++) {
                User u = userList.get(j);
                if (user.getId() == u.getParentId()) {
                    tempList.add(u);
                }
            }
            UserDto userDto;
            if (tempList.size()>0) {
                userDto = UserDto.adptChildren(user, tempList);
            } else {
                userDto = UserDto.transform(user);
            }
            userDtoList.add(userDto);
        }
        return userDtoList;
    }
}
