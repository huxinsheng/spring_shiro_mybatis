package com.hxs.ssm.form;

public class QueryForm extends BaseForm{
	
	boolean isPagination = false;

	private String sidx = "updateTime";

	private String sord = "desc";

	public boolean isPagination() {
		return isPagination;
	}

	public void setPagination(boolean isPagination) {
		this.isPagination = isPagination;
	}

	@Override
	public String getSidx() {
		return sidx;
	}

	@Override
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	@Override
	public String getSord() {
		return sord;
	}

	@Override
	public void setSord(String sord) {
		this.sord = sord;
	}
}
