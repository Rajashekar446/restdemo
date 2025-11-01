package com.example.restdemo.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    private final CustomUserDetailsService userDetailsService;

    public JwtFilter(JwtUtil jwtUtil, CustomUserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");
        String jwtToken = null;
        String username = null;

        //check for jwt token in Authorization header
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwtToken = authorizationHeader.substring(7);
            username = jwtUtil.getUsernameFromToken(jwtToken);
        }

        //If username exists and user not authenticated yet
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            //Load user details (roles, password hash, etc..)
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            //Validate Jwt token (check for signature, expiration, etc..)
            if (jwtUtil.validateToken(jwtToken)) {
                //create spring security authentication token
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                //Attach request details (IP, SESSION, etc.)
                authenticationToken.setDetails(authenticationToken.getDetails());

                // 6. Mark user as authenticated for this request
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            // else: token invalid/expired—user not authenticated
        }
        // 7. Pass control to next filter (or request handler)
        filterChain.doFilter(request, response);
    }
}
