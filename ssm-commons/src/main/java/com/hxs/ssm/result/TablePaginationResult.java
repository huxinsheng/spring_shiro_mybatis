package com.hxs.ssm.result;


import com.hxs.ssm.form.TablePaginationForm;

import java.util.List;

public class TablePaginationResult<T> extends TableResult<T> {

    private long iTotalRecords;

    private Object data;

    public TablePaginationResult(long count, List<T> list, TablePaginationForm form) {
        super(count, list);
        this.iTotalRecords = count;
    }

    public TablePaginationResult(long count, List<T> list) {
        super(count, list);
        this.iTotalRecords = count;
    }

    public TablePaginationResult(long count, List<T> list, Object data) {
        super(count, list);
        this.iTotalRecords = count;
        this.data = data;
    }

    public long getiTotalRecords() {
        return iTotalRecords;
    }

    public Object getData() {
        return data;
    }
}
