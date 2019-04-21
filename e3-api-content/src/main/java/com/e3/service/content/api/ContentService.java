package com.e3.service.content.api;

import com.e3.service.content.pojo.TbContent;
import com.e3.util.dto.EUDatagridResult;
import com.e3.util.dto.TreeResult;

import java.util.List;

public interface ContentService {
    List<TreeResult> getContentCategoryList(Long parentId);

    void saveContentCategory(Long parentId, String name);

    void updateContentCatgory(Long id, String name);

    void delContentCategory(Long id);

    void saveContent(TbContent tbContent);

    EUDatagridResult getContentList(int pageNum,int pageSize);
    //大广告位展示
    List<TbContent> getContentList1(long categoryId);
}
