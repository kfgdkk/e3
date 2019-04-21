package com.e3.service.content.impl;

import com.e3.jedis.dao.JedisClient;
import com.e3.service.content.api.ContentService;
import com.e3.service.content.mapper.TbContentCategoryMapper;
import com.e3.service.content.mapper.TbContentMapper;
import com.e3.service.content.pojo.TbContent;
import com.e3.service.content.pojo.TbContentCategory;
import com.e3.service.content.pojo.TbContentCategoryExample;
import com.e3.service.content.pojo.TbContentExample;
import com.e3.util.common.JsonUtils;
import com.e3.util.dto.EUDatagridResult;
import com.e3.util.dto.TreeResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;
    @Autowired
    private JedisClient jedisClient;
    @Autowired
    private TbContentMapper tbContentMapper;
    @Override
    public List<TreeResult> getContentCategoryList(Long parentId) {
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(example);
        List<TreeResult> resultList = new ArrayList<>();
        for (TbContentCategory cc : list) {
            TreeResult result = new TreeResult();
            result.setId(cc.getId());
            result.setText(cc.getName());
            result.setState(cc.getIsParent() ? "closed" : "open");
            resultList.add(result);
        }
        return resultList;
    }

    @Transactional
    @Override
    public void saveContentCategory(Long parentId, String name) {
        TbContentCategory tbContentCategory = new TbContentCategory();
        tbContentCategory.setCreated(new Date());
        tbContentCategory.setUpdated(new Date());
        tbContentCategory.setSortOrder(1);
        tbContentCategory.setStatus(1);
        tbContentCategory.setIsParent(false);
        tbContentCategory.setName(name);
        tbContentCategory.setParentId(parentId);
        tbContentCategoryMapper.insert(tbContentCategory);
        TbContentCategory cc = tbContentCategoryMapper.selectByPrimaryKey(parentId);
        if (!cc.getIsParent()) {
            cc.setIsParent(true);
            tbContentCategoryMapper.updateByPrimaryKey(cc);
        }
    }

    @Override
    public void updateContentCatgory(Long id, String name) {
        TbContentCategory tbContentCategory = tbContentCategoryMapper.selectByPrimaryKey(id);
        tbContentCategory.setName(name);
        tbContentCategoryMapper.updateByPrimaryKey(tbContentCategory);
    }

    @Override
    public void delContentCategory(Long id) {
        TbContentCategory tbContentCategory = tbContentCategoryMapper.selectByPrimaryKey(id);

        if (tbContentCategory.getParentId() != 0) {
            TbContentCategoryExample example = new TbContentCategoryExample();
            TbContentCategoryExample.Criteria c = example.createCriteria();
            c.andParentIdEqualTo(id);
            List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(example);
            if (list.size() > 0) {
                for (TbContentCategory cc : list) {
                    Long iid = cc.getId();
                    tbContentCategoryMapper.deleteByPrimaryKey(iid);
                    delContentCategory(iid);
                }
            }
            tbContentCategoryMapper.deleteByPrimaryKey(id);
        }
    }
    //保存广告位内容
    @Transactional
    @Override
    public void saveContent(TbContent tbContent) {
        jedisClient.hdel("IMAGE_INDEX_AD",tbContent.getCategoryId()+"");
        tbContent.setUpdated(new Date());
        tbContent.setCreated(new Date());
        tbContentMapper.insert(tbContent);
    }

    @Override
    public EUDatagridResult getContentList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<TbContent> list = (Page<TbContent>) tbContentMapper.selectByExample(null);
        return new EUDatagridResult(list.getTotal(),list.getResult());
    }

    //大广告位展示
    @Override
    public List<TbContent> getContentList1(long categoryId) {
        //2.取Redis中的数据
        String jsondatas= jedisClient.hget("IMAGE_INDEX_AD",categoryId+"");
        if(StringUtils.isNoneBlank(jsondatas)){
            List<TbContent>     tbContents = JsonUtils.jsonToList(jsondatas,TbContent.class);
            return tbContents;
        }
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<TbContent> list = tbContentMapper.selectByExample(example);
        jedisClient.hset("IMAGE_INDEX_AD",categoryId+"",JsonUtils.objectToJson(list));
        return list;
    }


}
