package com.e3.service.content;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by on 2017/10/15.
 */
public class ContentServiceProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContentServiceProvider.class);
    public static void main(String[] args) {
        try {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
            context.start();
        } catch (Exception e) {
            LOGGER.error("== DubboProvider context start error:",e);
        }
        synchronized (ContentServiceProvider.class) {
            while (true) {
                try {
                    ContentServiceProvider.class.wait();
                    System.out.println("内容广告位服务已启动成功！");
                } catch (InterruptedException e) {
                    LOGGER.error("== synchronized error:",e);
                }
            }
        }
    }
}