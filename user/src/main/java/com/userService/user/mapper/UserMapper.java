package com.userService.user.mapper;

import com.userService.user.DTO.UserDTO;
import com.userService.user.Entity.User;

public class UserMapper {

    public static UserDTO convertEntityToDto(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setDob(user.getDob());
        userDTO.setCreatedDate(user.getCreatedDate());
        userDTO.setId(user.getId());
        userDTO.setMobile(user.getMobile());
        userDTO.setEmailId(user.getEmailId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        return userDTO;
    }
}
