package com.hxs.ssm.form;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Field;
import java.util.Date;

public class BaseForm {

	public void trim() {
		try {
			Field[] fields = getClass().getDeclaredFields();
			for (Field field : fields) {
				if (field.getType() == String.class) {
					field.setAccessible(true);
					String value = (String) field.get(this);
					value = value.trim();
					field.set(this, value);
				}
			}
		} catch (Exception e) {
			Log logger = LogFactory.getLog(getClass());
			logger.error(null, e);
		}
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

	public String getsSearch() {
		return sSearch;
	}

	public void setsSearch(String sSearch) {
		this.sSearch = sSearch;
	}

	private Date createTime;

	private Date updateTime;

	private String creator;

	private String updateBy;

	private String sidx;

	private String sord;

	private String sSearch;
}
