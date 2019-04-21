package com.e3.service.search.pojo;


import java.io.Serializable;
import java.util.List;

public class SearchResult implements Serializable {
    private List<SearchPojo> itemList;
    private long recordCount;
    private long pageCount;
    private long curPage;

    public List<SearchPojo> getItemList() {
        return itemList;
    }

    public void setItemList(List<SearchPojo> itemList) {
        this.itemList = itemList;
    }

    public long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(long recordCount) {
        this.recordCount = recordCount;
    }

    public long getPageCount() {
        return pageCount;
    }

    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }

    public long getCurPage() {
        return curPage;
    }

    public void setCurPage(long curPage) {
        this.curPage = curPage;
    }
}
