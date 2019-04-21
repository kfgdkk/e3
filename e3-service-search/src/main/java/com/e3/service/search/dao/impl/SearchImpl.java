package com.e3.service.search.dao.impl;

import com.e3.service.search.dao.SearchDao;
import com.e3.service.search.pojo.SearchPojo;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class SearchImpl implements SearchDao {
    @Autowired
    private SolrServer solrServer;
    @Override
    public List<SearchPojo> getitemIndexList(SolrQuery solrQuery) {

        try {
            QueryResponse response  = solrServer.query(solrQuery);
            SolrDocumentList solr = response.getResults();
            List<SearchPojo> resultList = new ArrayList<>();
            for (SolrDocument document:solr){
                SearchPojo searchPojo = new SearchPojo();
                searchPojo.setId((String) document.get("id"));
                searchPojo.setImage((String) document.get("item_image"));
                searchPojo.setCatName((String) document.get("item_category_name"));
                searchPojo.setItemDesc((String) document.get("item_desc"));
                Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
                List<String> str = highlighting.get(document.get("id")).get("item_title");
                if (str!=null&&str.size()>0){
                    searchPojo.setTitle(str.get(0));
                }else {
                    searchPojo.setTitle((String) document.get("item_title"));
                }
                searchPojo.setSellPoint((String) document.get("item_sell_piont"));
                searchPojo.setPrice((Long) document.get("item_price"));
                resultList.add(searchPojo);
            }
            return resultList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
