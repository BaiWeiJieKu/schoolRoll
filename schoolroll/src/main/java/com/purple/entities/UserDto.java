package com.purple.entities;

import lombok.*;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto extends User {

    private List<User> children = new ArrayList<>();

    public static UserDto transform (User user) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    public static UserDto adptChildren (User user, List<User> userList) {
        UserDto userDto = transform(user);
        userDto.setChildren(userList);
        return userDto;
    }

}
