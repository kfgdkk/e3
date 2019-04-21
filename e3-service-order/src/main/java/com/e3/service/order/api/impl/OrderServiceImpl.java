package com.e3.service.order.api.impl;

import com.e3.jedis.dao.JedisClient;
import com.e3.service.goods.pojo.TbItem;
import com.e3.service.order.api.OrderService;
import com.e3.service.order.mapper.TbOrderItemMapper;
import com.e3.service.order.mapper.TbOrderMapper;
import com.e3.service.order.mapper.TbOrderShippingMapper;
import com.e3.service.order.pojo.OrderInfo;
import com.e3.service.order.pojo.TbOrderItem;
import com.e3.service.order.pojo.TbOrderShipping;
import com.e3.util.common.E3Result;
import com.e3.util.common.HttpClientUtil;
import com.e3.util.common.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private TbOrderMapper orderMapper;
    @Autowired
    private TbOrderShippingMapper orderShippingMapper;
    @Autowired
    private TbOrderItemMapper itemMapper;
    @Autowired
    private JedisClient jedisClient;

    @Override
    public void addCart(long userid, long itemid, Integer num) {
        //1.判断redis中是否有此数据
        Boolean hexists = jedisClient.hexists("CART_REDIS:" + userid, itemid + "");
        //如果有该商品
        if(hexists){
            //把商品信息取出,重新设置商品的数量,再写入到redis中
            String jsondata = jedisClient.hget("CART_REDIS:" + userid, itemid + "");
            //类型转换
            TbItem tbItem = JsonUtils.jsonToPojo(jsondata, TbItem.class);
            tbItem.setNum(num);//设置数量.
            //写入到redis中
            jedisClient.hset("CART_REDIS:" + userid, itemid + "",JsonUtils.objectToJson(tbItem));
        }
        //redis库中没有该条商品信息,
        //1.根据id从数据库中查询商品信息
        String jsondata =  HttpClientUtil.doGet("http://localhost:8080/e3-web-manager/item/"+itemid);
        TbItem tbItem = JsonUtils.jsonToPojo(jsondata,TbItem.class);
        //2.把商品信息,写入到redis中.
        jedisClient.hset("CART_REDIS:" + userid, itemid + "",JsonUtils.objectToJson(tbItem));
    }
    //合并购物车
    @Override
    public void megareCart(long userid, List<TbItem> list) {
        //1.判断cookie中是否有该商品
        //2如果有修改数量
        //3.写入到redis中
        for (TbItem items:list){
            addCart(userid,items.getId(), items.getNum());
        }
    }
    //展示购物车信息
    @Override
    public List<TbItem> showCart(long userid) {
        //从redis中获取数据
        List<String> list = jedisClient.hvals("CART_REDIS:" + userid);
        List<TbItem> result = new ArrayList<>();
        for(String s: list){
            TbItem  tbItem = new TbItem();
            result.add(JsonUtils.jsonToPojo(s,TbItem.class));//类型转换
        }
        return result;
    }

    @Transactional
    @Override
    public E3Result createOrder(OrderInfo orderInfo) {
        Boolean orderflag = jedisClient.exists("ORDER_ID");
        if (!orderflag){
            jedisClient.set("ORDER_ID","12222");
        }
        Long orderId = jedisClient.incr("ORDER_ID");
        orderInfo.setOrderId(orderId+"");
        orderInfo.setStatus(1);
        orderInfo.setCreateTime(new Date());
        orderMapper.insert(orderInfo);
        for (TbOrderItem orderItem : orderInfo.getOrderItems()){
            TbOrderItem  orderItem1 = new TbOrderItem();
            orderItem1.setId(jedisClient.incr("ORDER_ITEM")+"");
            orderItem1.setItemId(orderItem.getItemId());
            orderItem1.setOrderId(orderId+"");//订单号
            orderItem1.setNum(orderItem.getNum());//购买数量
            orderItem1.setTitle(orderItem.getTitle());//商品名称
            orderItem1.setPrice(orderItem.getPrice());//商品单价
            orderItem1.setTotalFee(orderItem.getTotalFee());//商品总金额
            orderItem1.setPicPath(orderItem.getPicPath());//图片地址
            itemMapper.insert(orderItem1);
        }
        TbOrderShipping orderShipping = orderInfo.getOrderShipping();
        orderShipping.setOrderId(orderId+"");
        orderShipping.setCreated(new Date());
        orderShipping.setUpdated(new Date());
        orderShippingMapper.insert(orderShipping);
        return E3Result.ok(orderId);
    }
}
