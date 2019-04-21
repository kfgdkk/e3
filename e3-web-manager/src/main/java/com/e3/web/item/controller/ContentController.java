package com.e3.web.item.controller;

import com.e3.service.content.api.ContentService;
import com.e3.service.content.pojo.TbContent;
import com.e3.util.common.E3Result;
import com.e3.util.dto.EUDatagridResult;
import com.e3.util.dto.TreeResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ContentController {
    @Autowired
    private ContentService contentService;
    @RequestMapping("/content/category/list")
    @ResponseBody
    public List<TreeResult> getContentList(@RequestParam(value = "id",defaultValue = "0") Long id){
        return contentService.getContentCategoryList(id);
    }
    @RequestMapping("/content/category/create")
    @ResponseBody
    public E3Result saveContentCategory(Long parentId,String name){
        try {
            contentService.saveContentCategory(parentId,name);
            return E3Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return E3Result.build(500,"保存失败");
        }
    }
    @RequestMapping("/content/category/update")
    @ResponseBody
    public E3Result updateContentCatgory(Long id,String name){
        try {
            contentService.updateContentCatgory(id,name);
            return E3Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return E3Result.build(500,"更新失败");
        }
    }
    @RequestMapping("/content/category/delete")
    @ResponseBody
    public E3Result deleteContentCa(Long id){

        try {
            contentService.delContentCategory(id);
            return E3Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return E3Result.build(500,"删除失败");
        }
    }
    //保存内容
    @RequestMapping("/content/save")
    @ResponseBody
    public E3Result  saveContent(TbContent tbContent){
        contentService.saveContent(tbContent);
        return  E3Result.ok();
    }
    @RequestMapping("/content/query/list")
    @ResponseBody
    public EUDatagridResult getContentList(int page,int rows){
        return contentService.getContentList(page,rows);
    }

}
