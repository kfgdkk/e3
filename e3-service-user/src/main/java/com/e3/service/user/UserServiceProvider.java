package com.e3.service.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2017/12/19 0019.
 */
public class UserServiceProvider {
    /**
     * Created by on 2017/10/15.
     */
        private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceProvider.class);
        public static void main(String[] args) {
            try {
                ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
                context.start();//把服务发布到zookeeper注册中心
            } catch (Exception e) {
                LOGGER.error("== DubboProvider context start error:",e);
            }
            synchronized (UserServiceProvider.class) {
                while (true) {
                    try {
                        UserServiceProvider.class.wait();
                        System.out.println("用户服务已启动成功！");
                    } catch (InterruptedException e) {
                        LOGGER.error("== synchronized error:",e);
                    }
                }
            }
        }
}
