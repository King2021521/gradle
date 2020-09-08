package com.zxm.gradle.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author King james
 * @Description
 * @Date 2020/9/3 0003 14:42
 * @Version 1.0
 */
@Service
@Slf4j
public class HttpService {
    @Autowired
    private RestTemplate restTemplate;

    private static final ExecutorService pool = Executors.newFixedThreadPool(20);

    private static List<String> elements;

    static {
        elements = new ArrayList<>();
        elements.add("https://blog.csdn.net/u012737673/article/details/107716368");
        elements.add("https://blog.csdn.net/u012737673/article/details/107714909");
        elements.add("https://blog.csdn.net/u012737673/article/details/107716368");
        elements.add("https://blog.csdn.net/u012737673/article/details/105837693");
        elements.add("https://blog.csdn.net/u012737673/article/details/105504600");
        elements.add("https://blog.csdn.net/u012737673/article/details/103416592");
        elements.add("https://blog.csdn.net/u012737673/article/details/103273685");
        elements.add("https://blog.csdn.net/u012737673/article/details/103273408");
        elements.add("https://blog.csdn.net/u012737673/article/details/103273282");
        elements.add("https://blog.csdn.net/u012737673/article/details/103272993");
        elements.add("https://blog.csdn.net/u012737673/article/details/89240144");
        elements.add("https://blog.csdn.net/u012737673/article/details/108360312");
        elements.add("https://blog.csdn.net/u012737673/article/details/108117503");
        elements.add("https://blog.csdn.net/u012737673/article/details/108006233");
        elements.add("https://blog.csdn.net/u012737673/article/details/82805257");
        elements.add("https://blog.csdn.net/u012737673/article/details/81303083");
        elements.add("https://blog.csdn.net/u012737673/article/details/79291531");
        elements.add("https://blog.csdn.net/u012737673/article/details/79295676");
        elements.add("https://blog.csdn.net/u012737673/article/details/79295744");
        elements.add("https://blog.csdn.net/u012737673/article/details/79295851");
        elements.add("https://blog.csdn.net/u012737673/article/details/79295791");
        elements.add("https://blog.csdn.net/u012737673/article/details/101194592");
    }

    @Async
    public void execute() throws InterruptedException {
        Random random = new Random(1);
        while (true) {
            elements.forEach(url -> {
                pool.submit(()->{
                    restTemplate.getForObject(url, String.class);
                    log.info("当前线程：{}，请求地址{}", Thread.currentThread().getName(), url);
                });
            });

            Thread.sleep((random.nextInt(20) + 60) * 1000);
        }
    }
}
