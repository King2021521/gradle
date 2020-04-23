package com.zxm.gradle.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author King james
 * @Description TODO
 * @Date 2020/4/23 0023 11:04
 * @Version 1.0
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {


    @GetMapping("/greeting")
    public String greeting() {
        return "Hello,World!";
    }

    @GetMapping("/login")
    public String login() {

        return "login sucess";
    }
}