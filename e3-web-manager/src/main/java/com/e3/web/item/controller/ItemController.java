package com.e3.web.item.controller;

import com.e3.service.goods.api.ItemService;
import com.e3.service.goods.pojo.TbItem;
import com.e3.util.common.E3Result;
import com.e3.util.dto.EUDatagridResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;
    @RequestMapping("/item/{itemId}")
    @ResponseBody
    public TbItem findItemById(@PathVariable Long itemId) {
        return itemService.getItemById(itemId);
    }
    @RequestMapping("/item/list")
    @ResponseBody
    public EUDatagridResult getitemList(int page, int rows){
        return itemService.getItemList(page,rows);
    }
    @RequestMapping("/item/save")
    @ResponseBody
    public E3Result saveItem(TbItem tbItem, String desc,String itemParams) throws Exception {
        return itemService.saveItem(tbItem,desc,itemParams);
    }


}
