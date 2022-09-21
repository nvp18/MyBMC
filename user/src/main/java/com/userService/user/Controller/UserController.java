package com.userService.user.Controller;

import com.userService.user.Entity.User;
import com.userService.user.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/users")
    @PreAuthorize("hasAuthority('POST_USER_INFO')")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User res = userService.createUser(user);
        return ResponseEntity.ok(res);
    }

    @GetMapping(value = "/users/{userID}")
    @PreAuthorize("hasAuthority('GET_USER_INFO')")
    public ResponseEntity<User> findUser(@PathVariable("userID") String userID) {
        User res = userService.findUser(userID);
        return ResponseEntity.ok(res);
    }

    @PostMapping(value = "/users/{id}/documents")
    public ResponseEntity<String> uploadDocuments(@PathVariable("id") String id, @RequestBody MultipartFile[] files) throws Exception {
        String res = userService.uploadDocuments(id,files);
        return ResponseEntity.ok(res);
    }
}
