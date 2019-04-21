package com.e3.service.goods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * Created by on 2017/10/15.
 */
public class GoodsServiceProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsServiceProvider.class);
    public static void main(String[] args) {
        try {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
            context.start();
        } catch (Exception e) {
            LOGGER.error("== DubboProvider context start error:",e);
        }
        synchronized (GoodsServiceProvider.class) {
            while (true) {
                try {
                    GoodsServiceProvider.class.wait();
                    System.out.println("商品服务已启动成功！");
                } catch (InterruptedException e) {
                    LOGGER.error("== synchronized error:",e);
                }
            }
        }
    }
}