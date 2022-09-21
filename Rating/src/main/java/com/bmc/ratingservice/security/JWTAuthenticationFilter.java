package com.bmc.ratingservice.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

@RequiredArgsConstructor
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UsernamePasswordModel usernamePasswordModel = new ObjectMapper().readValue(request.getInputStream(), UsernamePasswordModel.class);
            System.out.println(usernamePasswordModel.getPassword()+" "+usernamePasswordModel.getUserName());
            Authentication authentication = new UsernamePasswordAuthenticationToken(usernamePasswordModel.getUserName(),usernamePasswordModel.getPassword());
            return authenticationManager.authenticate(authentication);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String key="123456789876543218987653637253454543453";
        String token = Jwts.builder()
                        .setSubject(authResult.getName())
                        .claim("authorities",authResult.getAuthorities())
                        .setIssuedAt(new Date())
                        .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(1)))
                        .signWith(Keys.hmacShaKeyFor(key.getBytes())).compact();
        response.addHeader("authorization","Bearer "+token);
    }
}
