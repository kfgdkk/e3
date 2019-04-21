package com.e3.web.portal;

import com.e3.service.content.api.ContentService;
import com.e3.service.content.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class PageController {
    @Autowired
    private ContentService contentService;
    @RequestMapping("/index")
    public String showIndex(Model model){
        List<TbContent> list = contentService.getContentList1(89);
        model.addAttribute("ad1List",list);
        return "index";
    }
}
