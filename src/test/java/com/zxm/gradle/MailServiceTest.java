package com.zxm.gradle;

import com.zxm.gradle.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author King james
 * @Description TODO
 * @Date 2020/5/21 0021 9:50
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {
    @Autowired
    private MailService mailService;

    @Test
    public void testSend(){
        mailService.sendSimpleMail("1261608273@qq.com", "来自国王的邮件", "春眠不觉晓，处处闻啼鸟。夜来风雨声，花落知多少。");
    }
}
