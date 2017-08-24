package com.hxs.ssm.result;

import lombok.Data;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * @项目名称： hhdwworkspace
 * @类名称： MemberCreateResult
 * @类描述：
 * @创建人： huxinsheng
 * @创建时间： 2017-05-06 10:59
 * @联系方式： hxsysh@gmail.com
 * @修改备注：
 */
@Data
public class CreateResult extends WebApiResult {
    private Map resdtata;

    public CreateResult() {
        super();
    }

    public CreateResult(InputStream inStream) {
        super(inStream);
    }

    public void parseResult(JsonNode node) {
        ObjectMapper mapper = new ObjectMapper();
        String json = node.toString();
        try {
            Map map = mapper.readValue(json, Map.class);
            this.resdtata = map;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}