package com.example.demo;
import org.springframework.context.annotation.Configuration;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import javax.servlet.Filter;

@Configuration
public class CORSFilter implements Filter {

    public void destroy() {
    }
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        if (req instanceof HttpServletRequest && res instanceof HttpServletResponse) {
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) res;
            String origin = request.getHeader("Origin");
            response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE,PUT,PATCH,HEAD");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Headers",
                    "Origin, X-Requested-With, Content-Type, Accept, " + "X-CSRF-TOKEN");
            response.setHeader("Access-Control-Allow-Origin", Arrays.asList("http://localhost:3000").contains(origin) ? origin : "");
            response.setHeader("Vary", "Origin");
        }
        chain.doFilter(req, res);
    }
    public void init(FilterConfig filterConfig) {
    }
}