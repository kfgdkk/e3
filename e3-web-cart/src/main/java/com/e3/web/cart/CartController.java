package com.e3.web.cart;

import com.e3.jedis.dao.JedisClient;
import com.e3.service.goods.api.ItemService;
import com.e3.service.goods.pojo.TbItem;
import com.e3.service.user.pojo.TbUser;
import com.e3.util.common.CookieUtils;
import com.e3.util.common.E3Result;
import com.e3.util.common.JsonUtils;
import com.e3.web.cart.service.CartService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    private ItemService itemService;
    @Autowired
    private JedisClient jedisClient;
    @Autowired
    private CartService cartService;
    @RequestMapping("/cart/add/{itemId}")
    public String addCart(HttpServletRequest request, HttpServletResponse response,
    @PathVariable Long itemId,Integer num){
        //用户登陆后加入到redis缓存中
        TbUser user = (TbUser) request.getAttribute("users");
        if (user!=null){
            cartService.addCart(user.getId(),itemId,num);
            return "cartSuccess";
        }
        //没有登录,加入到cookie中
        boolean flag= false;
        List<TbItem> cartList = getCartList(request);
        if (cartList!=null&&cartList.size()>0) {
            for (TbItem item : cartList) {
                if (item.getId() == itemId) {
                    item.setNum(item.getNum() + num);
                    flag = true;
                    break;
                }
            }
        }
        if (!flag){
            TbItem tbItem = itemService.getItemById(itemId);
            cartList.add(tbItem);
        }
        CookieUtils.setCookie(request,response,"TT_CART",JsonUtils.objectToJson(cartList),true);
        return "cartSuccess";
    }
    private List<TbItem> getCartList(HttpServletRequest request){
        String jsonData = CookieUtils.getCookieValue(request, "TT_CART", true);
        List<TbItem> list = null;
        if (StringUtils.isNoneBlank(jsonData)){
             list =  JsonUtils.jsonToList(jsonData,TbItem.class);
            return list;
        }
        return new ArrayList<TbItem>();
    }

    @RequestMapping("/cart/cart")
    public String showCart(Model model,HttpServletRequest request,
                           HttpServletResponse response){
        List<TbItem> list = getCartList(request);
        TbUser user = (TbUser) request.getAttribute("users");
        if (user!=null){
            cartService.megareCart(user.getId(),list);
            list = cartService.showCart(user.getId());
            CookieUtils.deleteCookie(request,response,"TT_CART");
        }
        model.addAttribute("cartList", list);
        return "cart";
    }

    @RequestMapping("/cart/update/num/{itemId}/{num}")
    @ResponseBody
    public E3Result updateItemNum(HttpServletRequest request,HttpServletResponse response,
                                  @PathVariable Long  itemId, @PathVariable Integer num){
        List<TbItem> cartList = getCartList(request);
        for (TbItem item : cartList){
            if (item.getId()==itemId){
                item.setNum(num);
            }
        }
        CookieUtils.setCookie(request,response,"TT_CART",JsonUtils.objectToJson(cartList),true);
        return E3Result.ok();
    }
    //删除商品
    @RequestMapping("/cart/delete/{itemId}")
    public String deleteCart(HttpServletRequest  request,HttpServletResponse response,@PathVariable Long itemId){
        //1.取出购物车信息
        TbUser user = (TbUser) request.getAttribute("users");
        boolean hexists = jedisClient.hexists("CART_REDIS:" + user.getId(), itemId + "");
        if (hexists){
            jedisClient.hdel("CART_REDIS:" + user.getId(), itemId + "");
        }
        //1从cookie中读取购物车里的商品信息
        List<TbItem>  list = getCartList(request);
        //2.遍历
        for (TbItem items : list) {
            //3.找到相同的id
            if (items.getId() == itemId.longValue()) {
                //4.移除对象
                list.remove(items);
                break;
            }
        }
        //5.写入到cookie中
        CookieUtils.setCookie(request,response,"TT_CART",JsonUtils.objectToJson(list),true);//把商品放入到购物车

        //6.重新展示购物车页面
        return"redirect:/cart/cart.html";
    }


}
