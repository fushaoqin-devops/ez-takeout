package com.shaoqin.ez_take_out.filter;

import com.alibaba.fastjson.JSON;
import com.shaoqin.ez_take_out.common.R;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.io.IOException;

/**
 * ClassName: LoginCheckFilter
 * Package: com.shaoqin.ez_take_out.filter
 * Description: Check if user logged in
 * Author Shaoqin
 * Create 6/24/23 5:53 PM
 * Version 1.0
 */
@Component
@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {

    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestURI = request.getRequestURI();
        log.info("Request intercepted: {}", requestURI);

        // Requests that do not require login
        String[] urls = new String[] {
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**"
        };

        boolean isWhiteList = checkWhiteListUrl(requestURI, urls);

        if (isWhiteList) {
            filterChain.doFilter(request, response);
            return;
        }

        if (request.getSession().getAttribute("employee") != null) {
            filterChain.doFilter(request, response);
            return;
        }

        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;
    }

    public boolean checkWhiteListUrl(String requestURI, String[] urls) {
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if (match) return true;
        }
        return false;
    }

}
