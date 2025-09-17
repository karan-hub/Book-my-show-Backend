package com.cfs.bookMyShow.security;

import com.cfs.bookMyShow.exception.UserNotFoundException;
import com.cfs.bookMyShow.model.User;
import com.cfs.bookMyShow.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private  final UserRepository userRepository;
    private  final  AuthUtil authUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("Incoming  Request : {}" , request.getRequestURI());
        final  String requestHeader = request.getHeader("Authorization");
        if (requestHeader == null || !requestHeader.startsWith("Bearer")){
            filterChain.doFilter(request, response);
            return;
        }
        String  token  = requestHeader.split("Bearer ")[1];
        String userName = authUtil.getUserNameFromToken(token);

        if ( userName !=  null && SecurityContextHolder.getContext().getAuthentication()==null){
            User  user = userRepository.findByUsername(userName).orElseThrow(()-> new UserNotFoundException("user not found"));
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                    = new UsernamePasswordAuthenticationToken(user , null , null);

            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        filterChain.doFilter(request , response);



    }
}
