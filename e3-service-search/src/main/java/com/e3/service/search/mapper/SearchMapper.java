package com.e3.service.search.mapper;

import com.e3.service.search.pojo.SearchPojo;

import java.util.List;

public interface SearchMapper {
    List<SearchPojo> getItemSearch();

    SearchPojo getItemById(Long itemId);
}
