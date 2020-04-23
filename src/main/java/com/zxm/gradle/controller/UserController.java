package com.zxm.gradle.controller;

import com.alibaba.fastjson.JSONObject;
import com.zxm.gradle.config.JsonResult;
import com.zxm.gradle.entity.Book;
import com.zxm.gradle.service.BookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("${version}/gradle")
public class UserController {
    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private BookService bookService;

    @GetMapping(value = "/books")
    public Object getBookInfo() {
        List<Book> books = bookService.list();
        return books;
    }

    @GetMapping(value = "/books/{id}")
    public Object getBookInfo(@PathVariable Long id) {
        Book book = bookService.getById(id);
        logger.info("查询结果为：{}", JSONObject.toJSONString(book));
        return new JsonResult<>(book);
    }
}
