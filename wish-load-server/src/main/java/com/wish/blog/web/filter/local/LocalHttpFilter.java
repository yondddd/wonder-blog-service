package com.wish.blog.web.filter.local;

import jakarta.servlet.ServletException;

import java.io.IOException;

public interface LocalHttpFilter extends LocalFilter<LocalHttpContext, IOException, ServletException> {
    
    @Override
    void doFilter(LocalHttpContext context, LocalFilterChain<LocalHttpContext, IOException, ServletException> chain) throws IOException, ServletException;
    
}