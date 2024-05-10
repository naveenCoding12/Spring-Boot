package com.naveen.spring.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naveen.spring.Dto.UserVo;
import com.naveen.spring.Entity.User;
import com.naveen.spring.Service.UserService;

@RestController
@RequestMapping("/")
public class HomeController {

  @Autowired
  private UserService userService;

  @GetMapping("/home")
  public String home() {
    return "home.html";
  }

  @GetMapping("/login")
  public String login(){
    return "login.html";
  }
  @GetMapping("logout")
  public String logout(){
    return "logout.html";
  }

  @PostMapping("/save")
  public String save(@RequestBody UserVo userVo) {
    return userService.save(userVo);
  }

  @GetMapping("/allUsers")
  public List<User> listAll() {
    return userService.loadAll();
  }
}
