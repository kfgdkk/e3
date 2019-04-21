package com.e3.service.goods.api.impl;

import com.e3.service.goods.api.TbItemCatService;
import com.e3.service.goods.mapper.TbItemCatMapper;
import com.e3.service.goods.pojo.TbItemCat;
import com.e3.service.goods.pojo.TbItemCatExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TbItemCatServiceImpl implements TbItemCatService {
    @Autowired
    private TbItemCatMapper tbItemCatMapper;
    @Override
    public List<TbItemCat> queryByParentId(long parentId) {
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> tbItemCats = tbItemCatMapper.selectByExample(example);
        return tbItemCats;
    }
}
