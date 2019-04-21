package com.e3.util.dto;

import java.io.Serializable;
import java.util.List;

public class EUDatagridResult implements Serializable {
    private long total;
    private List<?> rows;

    public EUDatagridResult(long total, List<?> rows) {
        this.total = total;
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
