package com.zxm.gradle.controller;

import com.alibaba.fastjson.JSONObject;
import com.zxm.gradle.config.JsonResult;
import com.zxm.gradle.entity.Book;
import com.zxm.gradle.service.BookService;
import com.zxm.gradle.service.HttpService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("${version}/gradle")
public class UserController {
    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private BookService bookService;

    @Autowired
    private HttpService httpService;

    ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*2);

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

    @GetMapping(value = "/start")
    public void start() throws InterruptedException {
        httpService.execute();
    }

}
