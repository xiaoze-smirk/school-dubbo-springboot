package com.xiaoze.consumer.controller;

import java.util.Map;

import com.xiaoze.api.entity.User;
import com.xiaoze.consumer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * SecurityController
 *
 * @author xiaoze
 * @date 2018/6/12
 */
@Controller
@RequestMapping("/security")
public class SecurityController{

    @Autowired
    private UserService userService ;

    @GetMapping("/toLogin")
    public String toLogin(Map<String, Object> map) {

        map.put("user", new User());

        return "login";
    }


    @PostMapping(value="/login")
    public String login(User user,Map<String, Object> map){

        if(userService.get(user.getUserNo())!=null){
            User user1=userService.get(user.getUserNo());
            if(user1.getUserPwd().equals(user.getUserPwd())){
                map.put("user",user1);
                return "main";
            }
        }


        return "login";
    }

    @GetMapping("/mainController")
    public String main(){

        return "main";
    }

    @GetMapping("/logout")
    public String logout(){

        return "redirect:/security/toLogin";

    }

}