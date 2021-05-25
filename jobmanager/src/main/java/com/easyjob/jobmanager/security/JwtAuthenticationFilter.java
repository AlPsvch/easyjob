package com.easyjob.jobmanager.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.easyjob.jobmanager.util.SecurityConstants.JWT_TOKEN_HEADER;
import static com.easyjob.jobmanager.util.SecurityConstants.TOKEN_HEADER;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Resource(name = "userDetailsService")
    private UserDetailsService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getRequestURI().endsWith("/login") || request.getRequestURI().contains("/register")) {
            filterChain.doFilter(request, response);
            return;
        }

        String header = null;
        header = request.getHeader(JWT_TOKEN_HEADER);

        String login = null;
        String authToken = null;

        if (header != null && header.startsWith(TOKEN_HEADER)) {
            authToken = header.replace(TOKEN_HEADER, "");
            login = jwtTokenUtil.getUsernameFromToken(authToken);
        }

        if (login != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userService.loadUserByUsername(login);
            if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
