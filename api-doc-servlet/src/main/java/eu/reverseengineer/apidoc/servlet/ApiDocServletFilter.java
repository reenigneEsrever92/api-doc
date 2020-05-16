package eu.reverseengineer.apidoc.servlet;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import eu.reverseengineer.apidoc.api.IApiDocResult;

public class ApiDocServletFilter implements Filter {

    private String mediaType;
    private Pattern pattern;
    private IApiDocResult<String> result;

    public ApiDocServletFilter(String regex, String mediaType, IApiDocResult<String> result) {
        this.pattern = Pattern.compile(regex);
        this.result = result;
        this.mediaType = mediaType;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if(pattern.matcher(request.getServletContext().getContextPath()).matches()) {
            response.setContentType(mediaType);
            response.getWriter().write(result.get());
        }

        chain.doFilter(request, response);
    }
    
}