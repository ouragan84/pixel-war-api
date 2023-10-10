package com.ouragan.pixelwarapi;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SimpleCORSFilter implements Filter {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(SimpleCORSFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("Initilisation du Middleware");
        System.out.println("Initilisation du Middleware");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("the CORS filter was called!");

        HttpServletRequest requestToUse = (HttpServletRequest)servletRequest;
        HttpServletResponse responseToUse = (HttpServletResponse)servletResponse;

        responseToUse.setHeader("Access-Control-Allow-Origin", "*");
	responseToUse.setHeader("Access-Control-Allow-Methods","*");
        responseToUse.setHeader("Access-Control-Allow-Credentials", "true");
        responseToUse.setHeader("Access-Control-Allow-Headers", "*");
        filterChain.doFilter(requestToUse,responseToUse);
    }

    @Override
    public void destroy() {

    }
}
