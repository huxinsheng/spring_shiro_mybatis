package com.hxs.ssm.web;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class WebApplicationContextAware implements ApplicationContextAware {

    public void setApplicationContext(ApplicationContext context)
            throws BeansException {
        HttpContext.setApplicationContext(context);
    }

}
