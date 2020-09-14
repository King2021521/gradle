package com.zxm.gradle.utils;

import com.zxm.gradle.entity.Book;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author King james
 * @Description TODO
 * @Date 2020/6/4 0004 9:05
 * @Version 1.0
 */
public class TestThread {
    public static void main(String[] args) throws Exception {
        /*final CountDownLatch countDownLatch = new CountDownLatch(3);
        System.out.println("主线程开始执行----");
        ExecutorService pool = Executors.newFixedThreadPool(3);
        for(int i = 0;i < 3; i++){
            pool.execute(() -> {
                try {
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName()+" 执行完毕");
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                }
            });
        }

        System.out.println("主线程等待中----");
        countDownLatch.await();
        System.out.println("线程执行完毕");*/
        //testOptional();
        func(Arrays.asList("b", "c", "a"));
    }

    public static void testOptional() {
        Book book = null;
        Optional<Book> op = Optional.ofNullable(book);
        op.get().getBookName();
    }

    /**
     * Input string: data=('a', 'b','c')
     * <p>
     * Output string: aaa, aab, aac, aba, abb, abc, … ,cca, ccb, ccc
     *
     * @param input
     */
    public static void func(List<String> input) {
        //先将输入排序
        Collections.sort(input, (o1, o2) -> o1.compareTo(o2));
        System.out.println("输入内容：" + input);
        int length = input.size();
        int total = (int) Math.pow(length, length);
        System.out.println("输出总数" + total);

        List<String> output = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(input.get(i));

        }
    }
}
