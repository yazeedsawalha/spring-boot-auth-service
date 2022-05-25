package com.auth.authservice.web.filter;

import com.auth.authservice.domain.UserContext;
import com.auth.authservice.domain.UserContextHolder;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;

import static java.util.Collections.enumeration;

@Component
public class MainFilter implements Filter {

    public static final String LANGUAGE = "language";

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        try {
            final HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(httpServletRequest) {
                @Override
                public Enumeration<String> getHeaders(String name) {
                    if (HttpHeaders.CONTENT_TYPE.equalsIgnoreCase(name)) {
                        return enumeration(Collections.singleton(MediaType.APPLICATION_JSON_VALUE));
                    }
                    return super.getHeaders(name);
                }
            };
            String language = httpServletRequest.getHeader(LANGUAGE);
            UserContext userContext = new UserContext();
            userContext.setLanguage(language);

            UserContextHolder.set(userContext);
            filterChain.doFilter(wrapper, response);
        } finally {
            UserContextHolder.remove();
        }
    }

}
