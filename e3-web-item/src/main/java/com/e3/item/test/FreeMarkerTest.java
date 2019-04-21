package com.e3.item.test;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FreeMarkerTest {
    @Test
    public void genFile() throws Exception{
        Configuration configuration = new Configuration(Configuration.getVersion());
        configuration.setDirectoryForTemplateLoading(new File("E:/mikey/e3/e3-web-item/src/main/webapp/WEB-INF/ftl"));
        configuration.setDefaultEncoding("utf-8");
        Template template = configuration.getTemplate("hello.ftl");
        Map map = new HashMap();
        map.put("hello","this is my first freemarker");
        Writer out =  new FileWriter("E:/temp/hello.html");
        template.process(map,out);
        out.close();
    }
    @Test
    public void genFiles() throws Exception{
        Configuration configuration = new Configuration(Configuration.getVersion());
        configuration.setDirectoryForTemplateLoading(new File("E:/mikey/e3/e3-web-item/src/main/webapp/WEB-INF/ftl"));
        configuration.setDefaultEncoding("utf-8");
        Template template = configuration.getTemplate("Stu.ftl");
        Map map = new HashMap();
        Stu s = new Stu(0,"牛敏",24);
        Stu s1 = new Stu(1,"于倩",24);
        Stu s2 = new Stu(2,"于倩颖",24);
        map.put("stuList",s);
        Writer out =  new FileWriter("E:/temp/Stu.html");
        template.process(map,out);
        out.close();
    }
    @Test
    public void genFilew() throws Exception{
        Configuration configuration = new Configuration(Configuration.getVersion());
        configuration.setDirectoryForTemplateLoading(new File("E:/mikey/e3/e3-web-item/src/main/webapp/WEB-INF/ftl"));
        configuration.setDefaultEncoding("utf-8");
        Template template = configuration.getTemplate("StuS.ftl");
        Map map = new HashMap();
        Stu s = new Stu(0,"牛敏",24);
        Stu s1 = new Stu(1,"于倩",24);
        Stu s2 = new Stu(2,"于倩颖",24);
        List<Stu> list = new ArrayList<>();
        list.add(s);
        list.add(s1);
        list.add(s2);
        map.put("list",list);
        Writer out =  new FileWriter("E:/temp/StuS.html");
        template.process(map,out);
        out.close();
    }
    @Test
    public void genFiled() throws Exception{
        Configuration configuration = new Configuration(Configuration.getVersion());
        configuration.setDirectoryForTemplateLoading(new File("E:/mikey/e3/e3-web-item/src/main/webapp/WEB-INF/ftl"));
        configuration.setDefaultEncoding("utf-8");
        Template template = configuration.getTemplate("StuSs.ftl");
        Map map = new HashMap();
        Stu s = new Stu(0,"牛敏",24);
        Stu s1 = new Stu(1,"于倩",24);
        Stu s2 = new Stu(2,"于倩颖",24);
        List<Stu> list = new ArrayList<>();
        list.add(s);
        list.add(s1);
        list.add(s2);
        map.put("list",list);
        Writer out =  new FileWriter("E:/temp/StuSs.html");
        template.process(map,out);
        out.close();
    }

}
