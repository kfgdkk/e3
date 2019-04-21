package com.e3.web.cart.service.impl;

import com.e3.jedis.dao.JedisClient;
import com.e3.service.goods.pojo.TbItem;
import com.e3.util.common.HttpClientUtil;
import com.e3.util.common.JsonUtils;
import com.e3.web.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private JedisClient jedisClient;

    @Override
    public void addCart(long userid, long itemid, Integer num) {
        boolean hexists = jedisClient.hexists("CART_REDIS:" + userid, itemid + "");
        if (hexists) {
            String jsonData = jedisClient.hget("CART_REDIS:" + userid, itemid + "");
            TbItem tbItem = JsonUtils.jsonToPojo(jsonData, TbItem.class);
            tbItem.setNum(num);
            jedisClient.hget("CART_REDIS:" + userid, itemid + "");
        }
        String jsonData = HttpClientUtil.doGet("http://localhost:8080/e3-web-manager/item/" + itemid);
        TbItem tbItem = JsonUtils.jsonToPojo(jsonData, TbItem.class);
        jedisClient.hset("CART_REDIS:" + userid, itemid + "", JsonUtils.objectToJson(tbItem));
    }

    @Override
    public void megareCart(long userid, List<TbItem> list) {
        for (TbItem item : list) {
            addCart(userid, item.getId(), item.getNum());
        }
    }

    @Override
    public List<TbItem> showCart(long userid) {
        List<String> list = jedisClient.hvals("CART_REDIS:" + userid);
        List<TbItem> result= new ArrayList<>();
        for (String s :list){
            result.add(JsonUtils.jsonToPojo(s,TbItem.class));
        }
        return result;
    }
}
