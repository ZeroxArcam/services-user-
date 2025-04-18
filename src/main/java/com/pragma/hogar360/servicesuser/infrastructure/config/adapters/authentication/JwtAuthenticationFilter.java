package com.pragma.hogar360.servicesuser.infrastructure.config.adapters.authentication;

import com.pragma.hogar360.servicesuser.infrastructure.config.security.JwtService;
import com.pragma.hogar360.servicesuser.infrastructure.config.utils.constants.Constants; // Importa las constantes de infraestructura
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final ExtendedUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader(Constants.AUTHORIZATION_HEADER);
        final String jwt;
        final String userIdStr;

        if (authHeader == null || !authHeader.startsWith(Constants.BEARER_PREFIX)) {
            log.debug(Constants.JWT_TOKEN_NOT_FOUND_DEBUG);
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);
        userIdStr = jwtService.extractUsername(jwt);

        if (userIdStr != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                Long userId = Long.parseLong(userIdStr);
                UserDetails userDetails = userDetailsService.loadUserById(userId);

                if (userDetails != null && jwtService.isTokenValid(jwt, userDetails)) {
                    List<String> roles = (List<String>) jwtService.extractClaim(jwt, claims -> claims.get(Constants.ROLES_CLAIM));
                    log.info(Constants.ROLES_EXTRACTED_INFO, roles);

                    List<SimpleGrantedAuthority> authorities = roles.stream()
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList());

                    log.info(Constants.AUTHORITIES_BUILT_INFO, authorities);

                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            authorities
                    );
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    log.info(Constants.AUTHENTICATION_SET_INFO,
                            userId, authorities);
                }
            } catch (NumberFormatException e) {
                log.error(Constants.INVALID_USER_ID_FORMAT_ERROR, userIdStr);
            }
        }

        filterChain.doFilter(request, response);
    }
}