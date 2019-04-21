package com.e3.service.search.listener;

import com.e3.service.search.api.SearchService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class ItemChangeListener implements MessageListener {

    @Autowired
    private SearchService searchService;

    @Override
    public void onMessage(Message message) {
        try {
            TextMessage textMessage = (TextMessage) message;
            Long itemId = null;
            if (message instanceof TextMessage) {
                itemId = Long.parseLong(textMessage.getText());
            }
            Thread.sleep(1000);
            searchService.sysnIndex(itemId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
