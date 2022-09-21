package com.userService.user.Service.Impl;

import com.userService.user.Config.S3Repository;
import com.userService.user.DAO.UserDao;
import com.userService.user.DTO.UserDTO;
import com.userService.user.Entity.User;
import com.userService.user.Exception.ResourceNotFoundException;
import com.userService.user.Service.UserService;
import com.userService.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private S3Repository s3Repository;

    @Autowired
    private KafkaTemplate<String, UserDTO> kafkaTemplate;

    @Value("${user.topic}")
    private String kafkaTopic;

    @Override
    public User createUser(User user) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        user.setCreatedDate(simpleDateFormat.format(date));
        User savedUser = userDao.save(user);
        UserDTO userDTO = UserMapper.convertEntityToDto(savedUser);
        sendMessage(userDTO);
        return savedUser;
    }

    @Override
    public User findUser(String id) {
        Optional<User> res = userDao.findById(id);
        if(res.isPresent()) {
            return res.get();
        } else {
            throw new ResourceNotFoundException();
        }
    }

    @Override
    public String uploadDocuments(String id, MultipartFile[] files) throws Exception {
        for(MultipartFile multipartFile : files) {
            s3Repository.uploadFiles(id,multipartFile);
        }
        return "Documents Uploaded Successfully!!";
    }

    public void sendMessage(UserDTO userDTO) {
        kafkaTemplate.send(kafkaTopic,userDTO);
    }

}
