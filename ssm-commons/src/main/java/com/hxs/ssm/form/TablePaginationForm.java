package com.hxs.ssm.form;

public class TablePaginationForm extends QueryForm {

    private int iDisplayStart;

    private int iDisplayLength;

    public TablePaginationForm() {
        isPagination = true;
    }

    public int getiDisplayStart() {
        return iDisplayStart;
    }

    public void setiDisplayStart(int iDisplayStart) {
        this.iDisplayStart = iDisplayStart;
    }

    public int getiDisplayLength() {
        return iDisplayLength;
    }

    public void setiDisplayLength(int iDisplayLength) {
        this.iDisplayLength = iDisplayLength;
    }
}
