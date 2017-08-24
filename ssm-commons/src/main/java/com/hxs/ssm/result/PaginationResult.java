package com.hxs.ssm.result;


import com.hxs.ssm.form.PaginationForm;

import java.util.List;

public class PaginationResult<T> extends QueryResult<T> {

	private int currentPage;

	private int pageSize;

	public PaginationResult(long count, List<T> list , PaginationForm form) {
		super(count, list);
		this.currentPage = form.getCurrentPage();
		this.pageSize = form.getPageSize();
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

}
