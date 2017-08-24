package com.hxs.ssm.web.session;


import com.hxs.ssm.web.HttpSession;

public class SessionTomcatImpl extends HttpSession {

    private javax.servlet.http.HttpSession session;

    public SessionTomcatImpl(javax.servlet.http.HttpSession session) {
        this.session = session;
    }

    @Override
    public Object getAttribute(String key) {
        return session.getAttribute(key);
    }

    @Override
    public void setAttribute(String key, Object value) {
        session.setAttribute(key, value);
    }

    @Override
    public void remove(String key) {
        session.removeAttribute(key);
    }

    @Override
    public void abandon() {
        session.invalidate();
    }

}
