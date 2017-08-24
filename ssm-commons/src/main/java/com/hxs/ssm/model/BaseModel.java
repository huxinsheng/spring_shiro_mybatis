package com.hxs.ssm.model;

import lombok.Data;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Date;
import java.util.UUID;

@Data
public abstract class BaseModel {
	private Integer seqNo;

	private Date createTime;

	private Date updateTime;

	private String creator;

	private String updateBy;

	public static String newId() {
		return DigestUtils.md5Hex(UUID.randomUUID().toString());
	}

	public abstract String toJsonstring();
}
