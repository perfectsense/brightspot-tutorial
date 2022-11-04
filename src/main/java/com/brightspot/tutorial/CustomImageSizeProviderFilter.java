package com.brightspot.tutorial;

import java.util.Collections;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.psddev.cms.db.PageFilter;
import com.psddev.cms.image.ImageSizeProvider;
import com.psddev.dari.util.AbstractFilter;
import com.psddev.dari.util.RoutingFilter;
import com.psddev.dari.util.ThreadLocalStack;
import com.psddev.theme.ThemeFilter;

/**
 * Adds {@link CustomImageSizeProvider} to the ImageProvider stack.
 */
class CustomImageSizeProviderFilter extends AbstractFilter implements AbstractFilter.Auto {

    @Override
    protected Iterable<Class<? extends Filter>> dependencies() {
        return Collections.singletonList(ThemeFilter.class);
    }

    @Override
    public void updateDependencies(
            Class<? extends AbstractFilter> filterClass,
            List<Class<? extends Filter>> dependencies) {
        if (PageFilter.class.isAssignableFrom(filterClass)
                || RoutingFilter.class.isAssignableFrom(filterClass)) {
            dependencies.add(getClass());
        }
    }

    @Override
    protected void doRequest(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws Exception {
        ThreadLocalStack<ImageSizeProvider> providerStack = ImageSizeProvider.getCurrentStack();
    
        providerStack.push(new CustomImageSizeProvider(providerStack.peek()));
        try {
            chain.doFilter(request, response);

        } finally {
            providerStack.pop();
        }
    }

}
