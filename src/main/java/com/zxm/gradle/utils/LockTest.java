package com.zxm.gradle.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author King james
 * @Description 读写锁测试：1、读读不互斥；2、读写互斥（读锁先拿到了，要等读锁释放了，写锁才能进行；写锁先拿到，要等写锁释放，读锁才能进行）；3、写写互斥
 * j.u.c包下面的锁实现，底层都是基于AQS（一个FIFO的队列，用来实现同步锁及其他组件）
 * @Date 2020/9/15 0015 14:47
 * @Version 1.0
 */
public class LockTest {
    public static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    public static Lock read = lock.readLock();
    public static Lock write = lock.writeLock();

    public static List<Integer> data = new ArrayList<>();

    public static void main(String[] args) {
        new Thread(() -> read()).start();
        new Thread(() -> read()).start();
        new Thread(() -> read()).start();
        new Thread(() -> write()).start();
    }

    public static void write() {
        try {
            write.lock();
            System.out.println("线程："+Thread.currentThread().getName()+"已经拿到写锁。。。。。。");
            for (int i = 0; i < 100; i++) {
                data.add(i);
            }
            Thread.sleep(3000);
        }catch (Exception e){

        }finally {
            write.unlock();
            System.out.println("写锁已经释放。。。。");
        }
    }

    public static void read() {
        try {
            read.lock();
            System.out.println("线程："+Thread.currentThread().getName()+"已经拿到读锁。。。。。。");
            System.out.println("data数量为:" + data.size());
            Thread.sleep(10000);
        }catch (Exception e){

        }finally {
            read.unlock();
            System.out.println("读锁已经释放。。。。");
        }
    }
}
