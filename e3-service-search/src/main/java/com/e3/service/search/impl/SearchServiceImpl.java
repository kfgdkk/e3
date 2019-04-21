package com.e3.service.search.impl;

import com.e3.service.search.api.SearchService;
import com.e3.service.search.dao.SearchDao;
import com.e3.service.search.mapper.SearchMapper;
import com.e3.service.search.pojo.SearchPojo;
import com.e3.service.search.pojo.SearchResult;
import com.e3.util.common.E3Result;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private SearchMapper searchMapper;
    @Autowired
    private SolrServer solrServer;
    @Autowired
    private SearchDao searchDao;
    @Override
    public E3Result importItemIndex() {
        //查询数据库
        List<SearchPojo> list = searchMapper.getItemSearch();
        //把list集合里的数据遍历出来,放到索引库中.
        try {
            //循环数据添加到索引库
            for (SearchPojo  item: list){
                SolrInputDocument doc = new SolrInputDocument();
                doc.addField("id",item.getId());
                doc.addField("item_title",item.getTitle());
                doc.addField("item_sell_point",item.getSellPoint());
                doc.addField("item_price",item.getPrice());
                doc.addField("item_image",item.getImage());
                doc.addField("item_category_name",item.getCatName());
                doc.addField("item_desc",item.getItemDesc());
                solrServer.add(doc);
                solrServer.commit();
            }
            return E3Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public SearchResult searchItem(String query, Integer pageNow, Integer pageSize) {
        //根据搜索数据,查询内容
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.set("df", "item_keywords");//设置默认搜索域
        if (StringUtils.isNotBlank(query)) {
            solrQuery.setQuery(query);
        } else {
            solrQuery.setQuery("*:*");
        }
        //高亮显示
        solrQuery.setHighlight(true);
        solrQuery.setHighlightSimplePre("<font color='blue'>");
        solrQuery.setHighlightSimplePost("</font>");
        solrQuery.addHighlightField("item_title");//显示字段
        //分页信息
        //3.计算总页数

        if (pageNow < 0) pageNow = 1;
        if (pageNow == 0) pageNow = 1;
        //4.计算当前页
        int pageNows = (pageNow - 1) * pageSize;
        solrQuery.setStart(pageNows);
        solrQuery.setRows(pageSize);
        List<SearchPojo> list = searchDao.getitemIndexList(solrQuery);
        SearchResult result = new SearchResult();
        result.setItemList(list);
        result.setCurPage(pageNow);
        result.setRecordCount(list.size());
        //总页数
        int totalPage = (list.size() + pageSize - 1) / pageSize;
        result.setPageCount(totalPage);
        return result;
    }

    @Override
    public E3Result sysnIndex(Long itemId) {
        SearchPojo item = searchMapper.getItemById(itemId);
        try {
            SolrInputDocument document = new SolrInputDocument();
            document.addField("id",item.getId());
            document.addField("item_title",item.getTitle());
            document.addField("item_sell_point",item.getSellPoint());
            document.addField("item_price",item.getPrice());
            document.addField("item_desc",item.getItemDesc());
            document.addField("item_image",item.getImage());
            document.addField("item_category_name",item.getCatName());
            solrServer.add(document);
            solrServer.commit();
            return E3Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
