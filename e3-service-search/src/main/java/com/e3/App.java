package com.e3;

import com.e3.service.search.mapper.SearchMapper;
import com.e3.service.search.pojo.SearchPojo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-context.xml");
        SearchMapper mapper = context.getBean(SearchMapper.class);
        SearchPojo item = mapper.getItemById((long) 1474318759);
        System.out.println(item.getId()+"------"+item.getImage());
    }
}
