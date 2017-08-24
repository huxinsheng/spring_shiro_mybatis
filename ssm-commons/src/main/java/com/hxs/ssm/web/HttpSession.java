package com.hxs.ssm.web;


public abstract class HttpSession {

    public static final String CAPTCHA_SESSION_KEY = "captchaSessionKey";
    public static final String USER_SESSION_KEY = "userSessionKey";
    public static final String AUTH_SESSION_KEY = "authSessionKey";
    public static final String ENTRY_KIND_SESSION_KEY = "entryKindSessionKey";
    public static final String USER_MENU_LIST = "userMenuList";

    protected String sessionId;

    protected void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public abstract Object getAttribute(String key);

    public abstract void setAttribute(String key, Object value);

    public abstract void remove(String key);

    public abstract void abandon();
}
