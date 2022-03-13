package com.gteam.glog.config.jwtfilter;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        log.info("JWTExceptionHandlerFilter : start");
//        try {
//            log.info("JWTExceptionHandlerFilter : try inner");
//            filterChain.doFilter(request,response);
//        }catch (InvalidGraphException e){
//            log.info("JWTExceptionHandlerFilter : data invalid ");
//            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            response.setContentType("application/json");
//            response.getWriter().write(e.getMessage());
//        }catch (NullPointerException e){
//            log.info("JWTExceptionHandlerFilter : Data null ");
//            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            response.setContentType("application/json");
//            response.getWriter().write(e.getMessage());
//        }catch (IllegalArgumentException e){
            log.info("JWTExceptionHandlerFilter : Data not found ");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("application/json");
            response.getWriter().println("JWTExceptionHandlerFilter : Data not found ");

//        }

        return;
    }
}
