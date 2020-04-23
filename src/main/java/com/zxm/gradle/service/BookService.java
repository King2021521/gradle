package com.zxm.gradle.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxm.gradle.dao.BookMapper;
import com.zxm.gradle.entity.Book;
import org.springframework.stereotype.Service;

@Service
public class BookService extends ServiceImpl<BookMapper,Book> {
}
