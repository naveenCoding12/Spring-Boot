package com.naveen.spring.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naveen.spring.Dto.UserVo;
import com.naveen.spring.Entity.User;
import com.naveen.spring.Repository.UserRepo;

@Service
public class UserService {

  @Autowired
  private UserRepo userRepo;

  public String save(UserVo userVo) {

    User user = new User();
    user.setUserName(userVo.getUserName());
    user.setPassword(userVo.getPassword());

    userRepo.save(user);

    return "User Saved Successfully";
  }

  public List<User> loadAll() {
    return userRepo.findAll();
  }
}
