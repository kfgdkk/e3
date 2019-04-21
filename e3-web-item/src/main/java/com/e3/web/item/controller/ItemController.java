package com.e3.web.item.controller;

import com.e3.service.goods.api.ItemService;
import com.e3.service.goods.pojo.TbItem;
import com.e3.service.goods.pojo.TbItemDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;
    @RequestMapping("/item/{itemId}")
    public String showItem(@PathVariable Long itemId, Model model){
        TbItem item = itemService.getItemById(itemId);
        TbItemDesc itemDesc = itemService.getItemDesc(itemId);
        model.addAttribute("item",item);
        model.addAttribute("itemDesc",itemDesc);
        return "item";
    }
}
