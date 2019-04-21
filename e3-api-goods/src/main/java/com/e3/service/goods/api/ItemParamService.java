package com.e3.service.goods.api;

import com.e3.service.goods.pojo.TbItemParam;
import com.e3.util.common.E3Result;
import com.e3.util.dto.EUDatagridResult;

public interface ItemParamService {
    EUDatagridResult getItemParamList(int pageNum, int pageSize);

    E3Result getItemParamByCid(Long cid);

    E3Result saveItemParam(TbItemParam tbItemParam);

}
