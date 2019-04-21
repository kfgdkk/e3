package com.e3.service.goods.api.impl;

import com.e3.service.goods.api.ItemParamService;
import com.e3.service.goods.mapper.TbItemParamMapper;
import com.e3.service.goods.pojo.TbItemParam;
import com.e3.service.goods.pojo.TbItemParamExample;
import com.e3.util.common.E3Result;
import com.e3.util.dto.EUDatagridResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ItemParamServiceImpl implements ItemParamService {
    @Autowired
    private TbItemParamMapper paramMapper;
    @Override
    public EUDatagridResult getItemParamList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<TbItemParam> list = (Page<TbItemParam>) paramMapper.queryParamList();
        return new EUDatagridResult(list.getTotal(),list.getResult());
    }

    @Override
    public E3Result getItemParamByCid(Long cid) {
        TbItemParamExample itemParamExample = new TbItemParamExample();
        TbItemParamExample.Criteria criteria = itemParamExample.createCriteria();
        criteria.andItemCatIdEqualTo(cid);
        List<TbItemParam> list = paramMapper.selectByExampleWithBLOBs(itemParamExample);
        if (list!=null&&list.size()>0){
            return E3Result.ok(list.get(0));
        }
        return E3Result.ok();
    }

    @Transactional
    @Override
    public E3Result saveItemParam(TbItemParam tbItemParam) {
        tbItemParam.setCreated(new Date());
        tbItemParam.setUpdated(new Date());
        paramMapper.insert(tbItemParam);
        return E3Result.ok();
    }


}
