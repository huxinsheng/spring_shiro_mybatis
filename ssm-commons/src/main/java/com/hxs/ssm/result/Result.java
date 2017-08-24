package com.hxs.ssm.result;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public final class Result {

	private int errcode;
	
	private String errmsg = "";
	
	private Object[] arguments;
	
	private static Properties messages;
	
	private Object value;
	
	private Object result;
	
	//成功
	private final static int OK = 0;//成功
	//授权401
	public final static int LOGIN_REQUIRE = 4010;//需要登录
	public final static int LOGIN_PWD_ERROR = 4011;//登录密码错误
	public final static int LOGIN_DISABLED_ERROR = 4012;//登录帐号已禁用
	public final static int LOGIN_TOO_MANY_ERROR = 4013;//登录次数过多
	public final static int LOGIN_NON_PRIVILEGE_ERROR = 4014;//没有权限
	public final static int MENU_NON_PRIVILEGE_ERROR = 4015;//没有权限
	//超时408
	public final static int SESSION_TIMEOUT_ERROR = 4080;//会话超时
	//不存在404 
	public final static int NOT_FOUND = 4040;//用户不存在
	public final static int USER_NOT_FOUND = 4041;//用户不存在
	//内部500
	public final static int SERVER_ERROR = 5000;//服务器错误
	public final static int CAPTCHA_ERROR = 5001;//验证码错误
	public final static int USER_EXISTS = 5002;//用户存在
	public final static int EXISTS = 5003;//数据重复
	public final static int DATA_RANGE_EXISTS = 5004;//策略有效期重复
	//web api
	public final static int WXID_IS_EMPTY = 6001;//参数为空

	
	static{
		messages = new Properties();
		try {
			messages.load(Result.class.getResourceAsStream("/errors.properties"));
		} catch (Exception e) {
			LogFactory.getLog(Result.class).error(null , e);
		}
	}
	
	protected Result(){}
	
	public static Result error(){
		Result result = new Result();
		result.errcode = SERVER_ERROR;
		return result;
	}
	public static Result error(int code , String message){
		Result result = new Result();
		result.errcode = code;
		result.errmsg = message;
		return result;
	}
	
	public static Result error(int code , Object... arguments){
		Result result = new Result();
		result.errcode = code;
		result.arguments = arguments;
		return result;
	}
	
	public static Result error(String message , Object... arguments){
		Result result = new Result();
		result.errcode = SERVER_ERROR;
		result.errmsg = message;
		result.arguments = arguments;
		return result;
	}
	
	public static Result ok(){
		Result result = new Result();
		result.errcode = OK;
		return result;
	}
	
	public static Result ok(Object value){
		Result result = new Result();
		result.errcode = OK;
		result.value = value;
		return result;
	}
	
	public static Result ok(Object value , Object retVal){
		Result result = new Result();
		result.result = retVal;
		result.errcode = OK;
		result.value = value;
		return result;
	}
	
	public void setValue(Object value){
		this.value = value;
	}
	
	public void setResult(Object result){
		this.result = result;
	}
	 
	public <T extends Result> T transform(Class<T> clz){
		try {
			Result result = (Result)clz.newInstance();
			result.errcode = this.errcode;
			result.errmsg = this.errmsg;
			result.arguments = this.arguments;
			result.value = this.value;
			return (T)result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public int getErrcode(){
		return errcode;
	}
	
	public String getErrmsg(){
		String message = "";
		if(!StringUtils.isEmpty(errmsg)){
			message = errmsg;
		}else{
			String key = String.valueOf(errcode);
			if(messages.containsKey(key)){
				message = (String) messages.get(key);
			}
		}
		if(arguments != null){
			int len = arguments.length;
			for (int i = 0; i < len ; i++) {
				String num = "{" + i + "}";
				Object value = arguments[i];
				if(value == null){ 
					value = ""; 
				}
				message = message.replace(num , value.toString());
			}
		}
		return message;
	}
	

	@JsonIgnore
	public Object getValue() {
		return value;
	}
	
	public Object getResult() {
		return result;
	}

	@JsonIgnore
	public boolean isOK() {
		return errcode == OK;
	}
	
	public Map<String,Object> toMap(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("errcode", getErrcode());
		map.put("errmsg", getErrmsg());
		return map;
	}
}
