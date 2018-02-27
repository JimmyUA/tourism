package application.web.security.filters;

import application.entity.AppUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import static application.web.security.filters.SecurityConstants.*;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication
    attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {


            Map<String, String[]> map = request.getParameterMap();

            String username = map.get("username")[0];
            String password = map.get("password")[0];


            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    username,
                    password,
                    new ArrayList<>()
            ));

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String  token = Jwts.builder()
                .setSubject(((User)authResult.getPrincipal()).getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                .compact();

        String tokenValue = TOKEN_PREFIX + token;
        response.setHeader(HEADER_STRING, tokenValue);
        System.out.println("\n\n\n\n\n\n\n\n" + tokenValue);
        response.addCookie(new Cookie(COOKIE_TOKEN, tokenValue));
        response.sendRedirect("/home");
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.sendRedirect("/login");
    }
}
