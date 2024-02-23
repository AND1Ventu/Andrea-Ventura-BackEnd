package com.exercise.gestioneEventi.security;

import com.exercise.gestioneEventi.exception.UnAuthorizedException;
import com.exercise.gestioneEventi.model.User;
import com.exercise.gestioneEventi.service.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Arrays;

import jakarta.servlet.ServletException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTools jwtTools;
    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        if(authorization==null||!authorization.startsWith("Bearer ")){
            throw new UnAuthorizedException("Token non presente");
        }

        String token = authorization.substring(7);

        jwtTools.validateToken(token);

        String username = jwtTools.extractUsernameFromToken(token);

        User utente = userService.getUserByUsername(username);
//codice per autorizzare il servizio solo a utenti che cercano info sul proprio id/username
        checkPathVariable(request, utente);
//fine gestione precedente
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(utente, null, utente.getAuthorities());
        //authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        //authentication.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //System.out.println(authentication);

        filterChain.doFilter(request,response);

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }

    private void checkPathVariable(HttpServletRequest request, User utente){
        String[] parts = request.getServletPath().split("/");
        System.out.println(parts.length);
        Arrays.stream(parts).forEach(System.out::println);
        if(parts.length==3) {
            if (parts[1].equals("autori")) {
                int id = Integer.parseInt(parts[2]);

                if(utente.getId()!=id){
                    throw new UnAuthorizedException("Non sei abilitato ad utilizzare il servizio per id differenti dal tuo");
                }

            } else if (parts[1].equals("utenti")) {
                if(!utente.getUsername().equals(parts[2])){
                    throw new UnAuthorizedException("Non sei abilitato ad utilizzare il servizio per un username differente dal tuo");
                }
            }
        }
    }
}
