package com.userService.user.DAO;

import com.userService.user.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserDao extends MongoRepository<User, String> {

}
