package com.e3.service.goods.api;

import com.e3.service.goods.pojo.TbItem;
import com.e3.service.goods.pojo.TbItemDesc;
import com.e3.util.common.E3Result;
import com.e3.util.dto.EUDatagridResult;

public interface ItemService {

    EUDatagridResult getItemList(int pageNum, int pageSize);

    E3Result saveItem(TbItem tbItem, String desc, String itemParams) throws Exception;

    TbItem getItemById(Long itemId);

    TbItemDesc getItemDesc(long itemId);
}
