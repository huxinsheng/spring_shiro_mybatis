package com.hxs.ssm.form;

public class PaginationForm extends QueryForm{

	private int currentPage = 1;

	private int pageSize = 10;
	
	public PaginationForm(){
		isPagination = true;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getOffset(){
		return (currentPage - 1) * pageSize;
	}
}
