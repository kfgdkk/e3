package com.e3.service.order.api;

import com.e3.service.goods.pojo.TbItem;
import com.e3.service.order.pojo.OrderInfo;
import com.e3.util.common.E3Result;

import java.util.List;

public interface OrderService {
    void addCart(long userid, long itemid, Integer num);

    void megareCart(long userid, List<TbItem> list);

    List<TbItem> showCart(long userid);

    E3Result createOrder(OrderInfo orderInfo);
}
