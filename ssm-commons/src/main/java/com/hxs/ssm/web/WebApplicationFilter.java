package com.hxs.ssm.web;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class WebApplicationFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response,
						 FilterChain chain) throws IOException, ServletException {
		HttpContext.setCurrentRequest((HttpServletRequest)request);
		chain.doFilter(request, response);
	}

	public void init(FilterConfig config) throws ServletException {
		
	}

	public void destroy() {
		
	}
}
