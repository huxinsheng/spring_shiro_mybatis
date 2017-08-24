package com.hxs.ssm.utils.velocity;

import org.apache.velocity.tools.config.DefaultKey;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.map.ObjectMapper;

import java.util.HashMap;
import java.util.List;

@DefaultKey("json")
public class JsonTool extends org.apache.velocity.tools.generic.FormatConfig {

	public Object parse(Object content){
		if(content == null) return null;
		try{
			String json = (String)content;
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(Feature.ALLOW_SINGLE_QUOTES,true);
			mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS,true);
			JsonNode node  = mapper.readTree(json);
			if(node.isArray()){
				return mapper.convertValue(node, List.class);
			}else{
				return mapper.convertValue(node, HashMap.class);
			}
		}catch(Exception ex){
			throw new RuntimeException(ex);
		}
	}
}
