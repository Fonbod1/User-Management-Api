package com.KF48.User.Management.UserConfiguration.SwaggerConfiguration.Securityconfiguration1;//package com.KF48.User.Management.UserConfiguration.SwaggerConfiguration.Securityconfiguration;

import com.KF48.User.Management.UserService.JWService;
import com.KF48.User.Management.UserService.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JWService jwService;
    private final UserService userService;

    private static final List<String> OPEN_ROUTES = List.of(
            "/api/v1/auth/signup",
            "/api/v1/auth/signin",
            "/api/v1/auth/refresh",
            "/api/v1/auth",
            "/swagger-ui",
            "/v3/api-docs",
            "/swagger-resources",
            "/webjars",
            "/swagger-ui/index.html"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getServletPath();

        if (OPEN_ROUTES.stream().anyMatch(path::startsWith)) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");
        System.out.println("Authorization Header: " + authHeader);

        if (!StringUtils.hasText(authHeader) || !authHeader.startsWith("Bearer ")) {
            respondWithError(response, HttpServletResponse.SC_UNAUTHORIZED, "Authorization header is missing or invalid");
            return;
        }

        try {
            String jwt = authHeader.substring(7);
            String userEmail = jwService.extractUserName(jwt);

            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userService.userDetailsService().loadUserByUsername(userEmail);

                if (jwService.isTokenValid(jwt, userDetails)) {
                    // 🔥 Extract roles from JWT claims
                   /* Claims claims = Jwts.parserBuilder()

                            //.setSigningKey(jwService.getSigningKey()) // Make sure this method is public in your JWT service
                            .build()
                            .parseClaimsJws(jwt)
                            .getBody();*/

                    Claims claims = jwService.extractAllClaims(jwt); // ✅ Much cleaner


                    List<String> roles = claims.get("role", List.class);
                    List<SimpleGrantedAuthority> authorities = roles.stream()
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList());

                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                } else {
                    respondWithError(response, HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
                    return;
                }
            }
        } catch (ExpiredJwtException e) {
            respondWithError(response, HttpServletResponse.SC_UNAUTHORIZED, "Token has expired");
            return;
        } catch (Exception e) {
            respondWithError(response, HttpServletResponse.SC_UNAUTHORIZED, "Authentication failed: " + e.getMessage());
            return;
        }

        filterChain.doFilter(request, response);
    }

    private void respondWithError(HttpServletResponse response, int status, String message) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        response.getWriter().write("{\"error\": \"" + message + "\"}");
    }
}
