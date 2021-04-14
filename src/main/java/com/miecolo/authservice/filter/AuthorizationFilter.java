package com.miecolo.authservice.filter;

import com.miecolo.authservice.service.implementation.MyUserDetailsService;
import com.miecolo.authservice.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {



    @Autowired
    JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = httpServletRequest.getHeader("Authorization");
        String id = null ;
        String jwt = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
         try {
             jwt = authHeader.substring(7);
             id = jwtUtils.getUsernameFromToken(jwt);

             Map<String,Object> claims = jwtUtils.getAllClaimsFromToken(jwt);
             UserDetails userDetails = new User(id,"", (Collection<? extends GrantedAuthority>) claims.get("authorities"));
             if (jwtUtils.validateToken(jwt,userDetails)){
                 UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                 usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                 SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
             }
             filterChain.doFilter(httpServletRequest,httpServletResponse);

         }catch (Exception e){
             httpServletResponse.setHeader("error-message",e.getMessage());
             httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
         }

        }else {
            filterChain.doFilter(httpServletRequest,httpServletResponse);

        }

    }
}
