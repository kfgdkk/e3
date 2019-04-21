package com.e3.service.goods.api.impl;

import com.e3.jedis.dao.JedisClient;
import com.e3.service.goods.api.ItemService;
import com.e3.service.goods.mapper.TbItemDescMapper;
import com.e3.service.goods.mapper.TbItemMapper;
import com.e3.service.goods.mapper.TbItemParamItemMapper;
import com.e3.service.goods.pojo.TbItem;
import com.e3.service.goods.pojo.TbItemDesc;
import com.e3.service.goods.pojo.TbItemParamItem;
import com.e3.util.common.E3Result;
import com.e3.util.common.IDUtils;
import com.e3.util.common.JsonUtils;
import com.e3.util.dto.EUDatagridResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.jms.*;
import java.util.Date;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemDescMapper tbItemDescMapper;
    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;
    @Autowired
    private  JmsTemplate jmsTemplate;
    @Autowired
    private JedisClient jedisClient;
    @Resource(name = "topicDestination")
    private Destination destination;

    @Override
    public EUDatagridResult getItemList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<TbItem> items = (Page<TbItem>) tbItemMapper.selectByExample(null);
        return new EUDatagridResult(items.getTotal(),items.getResult());
    }

    @Transactional
    @Override
    public E3Result saveItem(TbItem tbItem, String desc, String itemParams) throws Exception{
        long itemId = IDUtils.genItemId();
        tbItem.setId(itemId);
        tbItem.setCreated(new Date());
        tbItem.setUpdated(new Date());
        tbItem.setStatus((byte)1);
        tbItemMapper.insert(tbItem);
        E3Result result = this.saveDesc(itemId, desc);
        if (result.getStatus()!=200){
            throw new Exception();
        }
        E3Result result1 = this.saveItemParam(itemId, itemParams);
        if(result1.getStatus()!=200){
            throw new Exception();
        }
        //topic
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                System.out.println(itemId);
                TextMessage textMessage = session.createTextMessage(itemId+"");
                return textMessage;
            }
        });
        return E3Result.ok();
    }

    @Override
    public TbItem getItemById(Long itemId) {
        String jsonData = jedisClient.get("REDIS_ITEM_KEY:"+itemId+":base");
        if (StringUtils.isNoneBlank(jsonData)){
            TbItem item = JsonUtils.jsonToPojo(jsonData, TbItem.class);
            return item;
        }
        TbItem tbItem = tbItemMapper.selectByPrimaryKey(itemId);
        jedisClient.set("REDIS_ITEM_KEY:"+itemId+":base",JsonUtils.objectToJson(tbItem));
        jedisClient.expire("REDIS_ITEM_KEY:"+itemId+""+":base",86400);
        return tbItem;
    }

    @Override
    public TbItemDesc getItemDesc(long itemId) {
        String jsonData = jedisClient.get("REDIS_ITEM_KEY:"+itemId+":desc");
        if (StringUtils.isNoneBlank(jsonData)){
            TbItemDesc  desc= JsonUtils.jsonToPojo(jsonData, TbItemDesc.class);
            return desc;
        }
        TbItemDesc tbItemDesc = tbItemDescMapper.selectByPrimaryKey(itemId);
        jedisClient.set("REDIS_ITEM_KEY:"+itemId+":desc",JsonUtils.objectToJson(tbItemDesc));
        jedisClient.expire("REDIS_ITEM_KEY:"+itemId+":base",86400);
        return tbItemDesc;
    }

    private E3Result saveDesc(long itemId,String desc){
        TbItemDesc desc1 = new TbItemDesc();
        desc1.setItemId(itemId);
        desc1.setItemDesc(desc);
        tbItemDescMapper.insert(desc1);
        return E3Result.ok();
    }
    private E3Result saveItemParam(Long itemId, String itemParams) {
        TbItemParamItem itemParamItem = new TbItemParamItem();
        itemParamItem.setCreated(new Date());
        itemParamItem.setUpdated(new Date());
        itemParamItem.setItemId(itemId);
        itemParamItem.setParamData(itemParams);
        tbItemParamItemMapper.insert(itemParamItem);
        return E3Result.ok();
    }
}
