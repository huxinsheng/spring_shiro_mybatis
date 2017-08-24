package com.hxs.ssm.result;

import lombok.ToString;
import lombok.extern.log4j.Log4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.InputStream;
import java.util.Properties;

@ToString
@Log4j
public abstract class WebApiResult {

    private String rescode;

    private String resmsg;

    private String restimestamp;


    private static Properties messages = new Properties();

    public WebApiResult() {
    }

    public WebApiResult(InputStream inStream) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode node = mapper.readTree(inStream);
            log.info(node.toString());
            if (node.has("rescode")) {
                rescode = node.get("rescode").asText();
                restimestamp = node.get("restimestamp").asText();
            }
            if (node.has("resmsg")) {
                resmsg = node.get("resmsg").asText();
            }
            if ("00000".equals(rescode)) {
                if (node.has("resdtata")) {
                    JsonNode dataNode = mapper.readTree(node.get("resdtata").asText());
                    parseResult(dataNode);
                }
            }
        } catch (Exception e) {
            Log logger = LogFactory.getLog(getClass());
            logger.error(null, e);
        }
    }

    public abstract void parseResult(JsonNode node);

    public boolean isOK() {
        return "00000".equals(rescode);
    }

    public String getRescode() {
        return rescode;
    }

    public String getResmsg() {
        return resmsg;
    }

    public String getRestimestamp() {
        return this.restimestamp;
    }

    public Result toResult() {
        return Result.error(getRescode(), getResmsg());
    }

    public void setRescode(String rescode) {
        this.rescode = rescode;
    }
}
