package com.e3.web.item.controller;

import com.e3.service.goods.api.ItemParamService;
import com.e3.service.goods.pojo.TbItemParam;
import com.e3.util.common.E3Result;
import com.e3.util.dto.EUDatagridResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemParamController {
    @Autowired
    private ItemParamService itemParamService;
    @RequestMapping("/item/param/list")
    @ResponseBody
    public EUDatagridResult getItemParam(int page,int rows){
        return itemParamService.getItemParamList(page,rows);
    }
    @RequestMapping("/item/param/query/itemcatid/{cid}")
    @ResponseBody
    public E3Result getItemParamByCid(@PathVariable Long cid){
        return itemParamService.getItemParamByCid(cid);
    }
    //保存规格参数模板
    @RequestMapping("/item/param/save/{cid}")
    @ResponseBody
    public E3Result   saveParam(@PathVariable Long cid, String paramData){
        TbItemParam param = new TbItemParam();
        param.setItemCatId(cid);
        param.setParamData(paramData);
        return itemParamService.saveItemParam(param);
    }
}
