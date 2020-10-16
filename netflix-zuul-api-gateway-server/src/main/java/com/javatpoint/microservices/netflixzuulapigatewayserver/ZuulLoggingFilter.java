package com.javatpoint.microservices.netflixzuulapigatewayserver;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
public class ZuulLoggingFilter extends ZuulFilter {

    private static final org.slf4j.Logger log =
            org.slf4j.LoggerFactory.getLogger(ZuulLoggingFilter.class);

    /*
     * This method classify filter by type.
     * 1. pre - pre-routing filter
     * 2. route - routing to an origin
     * 3. post - post-routing filter
     * 4. error - error handling
     * 5. static - static responses
     *
     * Any filter type can be created or added and run by calling the method
     * runFilters(type)
     * @return
     */
    @Override
    public String filterType() {
        //intercept the request before execution
        return "pre";
    }

    /*
     * This method defines the order for the filter.
     * Doesn't not need to be sequential
     * @return
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /*
     * This method checks the request and decides whether
     * filter to be executed or not.
     * @return a boolean value giving the result
     */
    @Override
    public boolean shouldFilter() {
        //should filter every request
        return true;
    }

    /*
     * This method is invoked only when !isFilterDisabled and shouldFilter are
     * both true
     * @return filtered result
     */
    @Override
    public Object run() throws ZuulException {

        //getting the HTTP request that is to be handled
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();

        //printing the details of the request
        log.info("request -> {} request uri-> {}" , request , request.getRequestURI());

        return null;
    }
}
