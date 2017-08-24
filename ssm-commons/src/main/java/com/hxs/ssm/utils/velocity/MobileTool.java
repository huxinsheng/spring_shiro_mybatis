package com.hxs.ssm.utils.velocity;

import org.apache.velocity.tools.config.DefaultKey;
import org.apache.velocity.tools.generic.FormatConfig;

@DefaultKey("mobile")
public class MobileTool extends FormatConfig {

    public Object parse(Object content) {
        if (content == null) return null;
        try {
            String mobile = (String) content;
            mobile = mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
            return mobile;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
