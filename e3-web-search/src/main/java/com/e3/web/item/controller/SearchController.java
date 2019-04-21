package com.e3.web.item.controller;

import com.e3.service.search.api.SearchService;
import com.e3.service.search.pojo.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SearchController {
    @Autowired
    private SearchService searchService;

    @RequestMapping("/search")
    public String searchItem(String keyword, @RequestParam(defaultValue = "0",value = "page")
            Integer page, Model model){
//        int i  = 1/0;全局异常
        SearchResult result = searchService.searchItem(keyword,page,60);
        model.addAttribute("itemList",result.getItemList());
        model.addAttribute("totalPages",result.getPageCount());
        model.addAttribute("page",result.getCurPage());
        model.addAttribute("recourdCount",result.getRecordCount());
        return "search";
    }

    @RequestMapping(value = "/httpclient/post",method = RequestMethod.POST)
    @ResponseBody
    public String getPost(String username,String password){
        return "username---"+username+"--password---"+password;
    }
}

