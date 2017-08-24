package com.hxs.ssm.utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map;

/**
 * @项目名称： hxs-ssm
 * @类名称： MemberCreateHelper
 * @类描述：
 * @创建人： huxinsheng
 * @创建时间： 2017-05-06 10:59
 * @联系方式： hxsysh@gmail.com
 * @修改备注：
 */
public class CreateHelper {
    public static String toJson(Map<String, Object> inData)
            throws IOException {
        // 创建数据JSON
        StringWriter writer = new StringWriter();
        JsonFactory factory = new JsonFactory();
        JsonGenerator json = factory.createJsonGenerator(writer);
        json.writeStartObject();
        if (!CollectionUtils.isEmpty(inData)) {
            Iterator<String> iter = inData.keySet().iterator();
            while (iter.hasNext()) {
                String key = iter.next();
                Object value = inData.get(key);
                json.writeObjectField(key, value);
            }
        }
        json.writeEndObject();
        json.close();
        return writer.toString();
    }
}
