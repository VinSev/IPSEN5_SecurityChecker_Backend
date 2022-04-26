package nl.hsleiden.IPSEN5_SecurityChecker_Backend.security;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final String HEADER = "Authorization";
    private final String IDENTIFIER = "Bearer ";

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        final Map<String, String> map = getToken(request);
        String token = map.get("token");
        String email = map.get("email");

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByEmail(email);

            if (jwtUtil.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }

    public Map<String, String> getToken(HttpServletRequest httpServletRequest) {
        Map<String, String> map = new HashMap<>();
        String authorizationHeader = httpServletRequest.getHeader(HEADER);

        map.put("token", null);
        map.put("email", null);

        if (authorizationHeader != null && authorizationHeader.startsWith(IDENTIFIER)) {
            String token = authorizationHeader.substring(7);
            map.put("token", token);
            map.put("email", jwtUtil.extractEmail(token));
        }
        return map;
    }
}
