package com.userService.user.Service;

import com.userService.user.Entity.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    User createUser(User userDTO);

    User findUser(String id);

    String uploadDocuments(String id, MultipartFile[] files) throws Exception;
}
