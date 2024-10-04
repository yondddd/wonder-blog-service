package com.wish.blog.web.filter.local;


import jakarta.servlet.ServletException;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.collections.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LocalHttpFilterChain implements LocalFilterChain<LocalHttpContext, IOException, ServletException> {
    
    @NotNull
    private List<LocalHttpFilter> filters;
    
    @NotNull
    private LocalHttpFilter lastFilter;
    
    private int size = -1;
    
    private static final LocalHttpFilterChain DEFAULT_CHAIN = new LocalHttpFilterChain();
    
    private LocalHttpFilterChain() {
    }
    
    public static LocalHttpFilterChain custom() {
        return new LocalHttpFilterChain();
    }
    
    public static LocalHttpFilterChain getDefaultChain() {
        return DEFAULT_CHAIN;
    }
    
    public static List<LocalHttpFilter> getDefaultFilters() {
        return DEFAULT_CHAIN.filters;
    }
    
    public LocalHttpFilterChain setLastFilter(@NotNull LocalHttpFilter lastFilter) {
        this.lastFilter = lastFilter;
        return this;
    }
    
    public LocalHttpFilterChain setFilters(@NotNull List<LocalHttpFilter> filters) {
        this.filters = filters;
        return this;
    }
    
    public LocalHttpFilterChain addFilter(@NotNull LocalHttpFilter filter) {
        if (null == filter) {
            return this;
        }
        
        this.checkFilters();
        this.filters.add(filter);
        return this;
    }
    
    private void checkFilters() {
        if (filters == null) {
            filters = new ArrayList<>(2);
        }
    }
    
    @Override
    public void doFilter(LocalHttpContext localHttpContext) throws IOException, ServletException {
        this.size++;
        if (CollectionUtils.isEmpty(filters) || this.size >= this.filters.size()) {
            this.lastFilter.doFilter(localHttpContext, this);
        } else {
            this.filters.get(size).doFilter(localHttpContext, this);
        }
    }
    
}