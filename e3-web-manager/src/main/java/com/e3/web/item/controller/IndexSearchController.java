package com.e3.web.item.controller;

import com.e3.service.search.api.SearchService;
import com.e3.util.common.E3Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexSearchController {
    @Autowired
    private SearchService searchService;
    @RequestMapping("/index/item/import")
    @ResponseBody
    public E3Result indexSearch(){
        return searchService.importItemIndex();
    }
}
