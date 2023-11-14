package com.parcialUno.merchandises.filter;

import com.parcialUno.merchandises.service.AuthService;
import com.parcialUno.merchandises.utils.JWTUtil;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {
    private final AuthService authService;
    private final JWTUtil jwtTokenUtil;

    public JwtRequestFilter(AuthService authService, JWTUtil jwtTokenUtil){
        this.authService = authService;
        this.jwtTokenUtil = jwtTokenUtil;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");
        try {
            if (requestTokenHeader != null) {
                String jwtToken = requestTokenHeader.substring(7);
                String username = jwtTokenUtil.getValue(jwtToken);
                if (StringUtils.isNotEmpty(username) && null == SecurityContextHolder.getContext().getAuthentication()) {
                    UserDetails userDetails = authService.loadUserByUsername(username);
                    if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new
                                UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    }
                }
            }
        } catch (Exception exception) {
            log.error("Error with JWT token ({}); Exception {} ({})",requestTokenHeader, "User" , exception.getMessage());
        }
        filterChain.doFilter(request, response);
    }
}
