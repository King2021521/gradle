package com.zxm.gradle.controller;

import com.zxm.gradle.entity.Book;
import com.zxm.gradle.service.BookService;

import java.util.List;

/**
 * @Author King james
 * @Description TODO
 * @Date 2020/4/23 0023 13:53
 * @Version 1.0
 */
public class Task implements Runnable{

    private BookService bookService;

    List<Book> books;

    Task(BookService bookService, List<Book> books){
        this.bookService = bookService;
        this.books = books;
    }

    @Override
    public void run() {
        bookService.saveBatch(books);
    }
}
