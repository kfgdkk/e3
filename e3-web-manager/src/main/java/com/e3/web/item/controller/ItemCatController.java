package com.e3.web.item.controller;

import com.e3.service.goods.api.TbItemCatService;
import com.e3.service.goods.pojo.TbItemCat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ItemCatController {
    @Autowired
    private TbItemCatService tbItemCatService;

    @RequestMapping("/item/cat/list")
    @ResponseBody
    public List queryItemCat(@RequestParam(value = "id", defaultValue = "0") long id) {
        List<TbItemCat> tbItemCats = tbItemCatService.queryByParentId(id);
        List result = new ArrayList();
        for (TbItemCat itemCat : tbItemCats) {
            Map map = new HashMap();
            map.put("id", itemCat.getId());
            map.put("text", itemCat.getName());
            map.put("state", itemCat.getIsParent() ? "closed" : "open");
            result.add(map);
        }
        return result;
    }
}
