package com.hxs.ssm.model.core;

import lombok.Data;

@Data
public class Bill {

    private String code;

    private String prefix;

    private String dateFormat;

    private int num;
}
