package com.e3.web.controller;

import com.e3.service.goods.pojo.TbItem;
import com.e3.service.order.api.OrderService;
import com.e3.service.order.pojo.OrderInfo;
import com.e3.service.order.pojo.TbOrder;
import com.e3.service.user.pojo.TbUser;
import com.e3.util.common.CookieUtils;
import com.e3.util.common.E3Result;
import com.e3.util.common.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;
    @RequestMapping("/order/order-cart")
    public String showCart(Model model, HttpServletRequest request, HttpServletResponse response){
        List<TbItem> lists = getCartList(request);//从cookie中取的信息
        TbUser tbUser = (TbUser) request.getAttribute("orderUser");//取作用域中的user信息
        if (tbUser!=null){//判断是否为空
            orderService.megareCart(tbUser.getId(),lists);//合并购物车
            //展示的是redis中购物车的信息
            lists = orderService.showCart(tbUser.getId());
            //清空cookie;
            CookieUtils.deleteCookie(request,response,"TT_CART");
        }
        model.addAttribute("cartList",lists);
        return "order-cart";
    }

    //从cookie中取数据
    private List<TbItem> getCartList(HttpServletRequest request){
        String jsonData =   CookieUtils.getCookieValue(request,"TT_CART",true);
        if(StringUtils.isNotBlank(jsonData)){
            return  JsonUtils.jsonToList(jsonData,TbItem.class);
        }
        return  new ArrayList<TbItem>();
    }

    //提交订单
    @RequestMapping("/order/create")
    public String  createOrder(HttpServletRequest request, Model model, OrderInfo orderInfo){
        //获取用户信息
        TbUser tbUser = (TbUser) request.getAttribute("orderUser");
        TbOrder tbOrder = new TbOrder();
        tbOrder.setUserId(tbUser.getId());
        tbOrder.setBuyerNick(tbUser.getUsername());
        E3Result order = orderService.createOrder(orderInfo);
        long orderid = (long) order.getData();
        model.addAttribute("orderId",orderid);
        String payment = orderInfo.getPayment();//总价格
        model.addAttribute("payment",payment);
        return "success";
    }
}
