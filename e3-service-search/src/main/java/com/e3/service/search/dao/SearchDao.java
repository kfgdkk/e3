package com.e3.service.search.dao;

import com.e3.service.search.pojo.SearchPojo;
import org.apache.solr.client.solrj.SolrQuery;

import java.util.List;

public interface SearchDao {
    List<SearchPojo> getitemIndexList(SolrQuery solrQuery);
}
