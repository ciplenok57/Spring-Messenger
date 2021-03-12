package com.mgupi.messenger.filter;

import com.mgupi.messenger.repository.CustomUserRepository;
import com.mgupi.messenger.service.CustomUserDetailsService;
import com.mgupi.messenger.service.JWTUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {

    public JWTFilter(JWTUtil jwtUtil, CustomUserDetailsService customUserDetailsService, CustomUserRepository customUserRepository) {
        this.jwtUtil = jwtUtil;
        this.customUserDetailsService = customUserDetailsService;
        this.customUserRepository = customUserRepository;
    }

    private final JWTUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;
    private final CustomUserRepository customUserRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = jwtUtil.getLoginFromToken(jwt);
        }

        if (jwtUtil.validateToken(jwt)){
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }

}
