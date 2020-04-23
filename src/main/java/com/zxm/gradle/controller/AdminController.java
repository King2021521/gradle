package com.zxm.gradle.controller;

import com.zxm.gradle.config.JsonResult;
import com.zxm.gradle.entity.Book;
import com.zxm.gradle.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author King james
 * @Description TODO
 * @Date 2020/4/23 0023 11:04
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public JsonResult<List> greeting() {
        List<Book> books = bookService.list();
        return new JsonResult<>(books);
    }

    @GetMapping("/login")
    public String login() {
        return "login sucess";
    }
}
