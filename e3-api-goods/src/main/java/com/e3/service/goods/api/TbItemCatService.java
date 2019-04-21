package com.e3.service.goods.api;

import com.e3.service.goods.pojo.TbItemCat;

import java.util.List;

public interface TbItemCatService {
    List<TbItemCat> queryByParentId(long parentId);
}
