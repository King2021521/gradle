package com.zxm.gradle;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zxm.gradle.entity.Book;
import com.zxm.gradle.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisplusApplicationTests {
    @Autowired
    private BookService bookService;

    ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*2);

    /**
     * 单次插入
     */
    @Test
    public void save() {
        for(int j=0;j<1000;j++){
            List<Book> list = new ArrayList<>();
            for(int i=0;i<5000;i++){
                Book book = new Book();
                book.setBookName("think in java");
                book.setBookAuthor("kd"+i);
                book.setBookPrice(BigDecimal.valueOf(66.66));
                book.setPushDate(new Date());
                list.add(book);
            }

            Task t = new Task(bookService, list);
            pool.submit(t);
            System.out.println("第"+j+"批任务已完成");
        }
    }

    /**
     * 批量插入
     */
    @Test
    public void saveBatch() {
        List<Book> bookList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Book book = new Book();
            book.setBookName("Think in java" + i);
            book.setBookAuthor("zxm");
            book.setBookPrice(BigDecimal.valueOf(33.33));
            book.setPushDate(new Date());
            bookList.add(book);
        }
        // 这里saveBatch建立了事务, 一旦出现异常会自动回滚
        boolean b = bookService.saveBatch(bookList);
        System.out.println(b);
    }

    /**
     * 删除我们将两种特殊方式
     * 1. 按列删除 removeByMap()
     * 2. 按条件删除 remove(Wrap)
     */
    @Test
    public void delete() {

        // DELETE
        //  FROM t_book
        //  WHERE book_id = 10;
        Map<String, Object> columnMap = new HashMap<>();
        // 注意这里的 key => 用的是数据库的字段
        columnMap.put("book_id", 10);
        boolean removeByMap = bookService.removeByMap(columnMap);
        System.out.println(removeByMap);

        // DELETE
        // FROM t_book
        // WHERE book_author = 'pyi' AND book_price BETWEEN '30' AND '40' AND push_date >= '2019-04-10 04:03:21';
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("book_author", "pyi")
                .between("book_price", "30", "40")
                .ge("push_date", "2019-04-10 04:03:21");
        System.out.println(bookService.remove(queryWrapper));
    }

    /**
     * 修改我们只讨论条件修改的方式
     */
    @Test
    public void update() {
        // UPDATE t_book SET book_name = concat(book_name, '_1'),book_price=44.44
        // WHERE book_author = 'pyi' AND book_id > 2;
        UpdateWrapper<Book> updateWrapper = new UpdateWrapper<>();
        Map<String, Object> updateMap = new HashMap<>();
        updateMap.put("book_author", "pyi");
        updateWrapper.setSql("book_name = concat(book_name, '_1')")
                .set("book_price", 44.44)
                .allEq(updateMap)
                .gt("book_id", 2);
        System.out.println(bookService.update(updateWrapper));
    }

    @Test
    public void select() {

        // 根据主键查询
        System.out.println(bookService.getById(1));

        // 根据条件查询并获取部分字段 (注意 getMap只会返回一个Map 对象, 要是条件满足多个则会返回第一个)
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("book_id", "book_name")
                .ge("book_id", 1)
                .last(" limit 1");
        System.out.println(bookService.getMap(queryWrapper));

        // getOne 返回一个对象, 要是有多个对象返回第一个, 注意要是 第二个参数true(默认) 那么返回多个对象就会抛出异常
        System.out.println(bookService.getOne(queryWrapper, false));

        // list 返回多个对象集合
        bookService.list(new QueryWrapper<Book>().gt("book_id",1)).forEach(System.out::println);
    }

    /**
     * 注意, 要使用分页必配置 PaginationInterceptor到Spring容器中
     */
    @Test
    public void pagination() {

        // SELECT COUNT(1) FROM t_book;
        // SELECT book_id,book_name,book_author,book_price,push_date FROM t_bookLIMIT 1,1;

        /*Page<Book> bookPage = new Page<>(2, 1);
        IPage<Book> bookIPage = bookService.page(bookPage, Wrappers.emptyWrapper());
        bookIPage.getRecords().forEach(System.out::println);*/
    }

    class Task implements Runnable{

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
}
