package com.hxs.ssm.result;

import java.util.List;

public class QueryResult<T> {
	
	private List<T> rows;
	
	private long count;
	
	private Object obj;

	protected QueryResult(long count , List<T> rows){
		this.count = count;
		this.rows = rows;
	}
	
	public QueryResult(List<T> rows){
		this.count = rows.size();
		this.rows = rows;
	}

	public List<T> getRows() {
		return rows;
	}

	public long getCount() {
		return count;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	
}
