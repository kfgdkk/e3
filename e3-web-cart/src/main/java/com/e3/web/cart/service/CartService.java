package com.e3.web.cart.service;

import com.e3.service.goods.pojo.TbItem;

import java.util.List;

public interface CartService {
    void addCart(long userid, long itemid, Integer num);

    void megareCart(long userid, List<TbItem> list);

    List<TbItem> showCart(long userid);
}
