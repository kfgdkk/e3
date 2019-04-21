package com.e3.service.search.api;

import com.e3.service.search.pojo.SearchResult;
import com.e3.util.common.E3Result;

public interface SearchService {
    E3Result importItemIndex();

    SearchResult searchItem(String query, Integer pageNow, Integer pageSize);

    E3Result sysnIndex(Long itemId);
}
