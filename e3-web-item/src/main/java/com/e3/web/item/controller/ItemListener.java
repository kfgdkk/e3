package com.e3.web.item.controller;

import com.e3.service.goods.api.ItemService;
import com.e3.service.goods.pojo.TbItem;
import com.e3.service.goods.pojo.TbItemDesc;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class ItemListener implements MessageListener {
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;
    @Autowired
    private ItemService itemService;

    @Override
    public void onMessage(Message message) {
        try {
            TextMessage textMessage = (TextMessage) message;
            String s  = textMessage.getText();
            Long itemId = Long.parseLong(s);
            Thread.sleep(1000);
            TbItemDesc desc = itemService.getItemDesc(itemId);
            TbItem item = itemService.getItemById(itemId);
            Configuration configuration = freeMarkerConfigurer.getConfiguration();
            Template template = configuration.getTemplate("item.ftl");
            Map map = new HashMap();
            map.put("itemDesc",desc);
            map.put("item",item);
            Writer out = new FileWriter(new File("E:/temp/"+s+".html"));
            template.process(map,out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
