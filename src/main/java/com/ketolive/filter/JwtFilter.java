package com.ketolive.filter;

import com.ketolive.util.JwUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwUtil jwtUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if(token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            if(jwtUtil.validateToken(token)) {
                filterChain.doFilter(request, response);
                return;
            }
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("Token is not valid");
    }
}
