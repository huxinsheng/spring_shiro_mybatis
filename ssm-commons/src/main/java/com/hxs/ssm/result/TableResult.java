package com.hxs.ssm.result;

import java.util.List;

public class TableResult<T> {

    private List<T> rows;

    private long iTotalDisplayRecords;

    protected TableResult(long count, List<T> rows) {
        this.iTotalDisplayRecords = count;
        this.rows = rows;
    }

    public TableResult(List<T> rows) {
        this.iTotalDisplayRecords = rows.size();
        this.rows = rows;
    }

    public List<T> getRows() {
        return rows;
    }

    public long getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }
}
