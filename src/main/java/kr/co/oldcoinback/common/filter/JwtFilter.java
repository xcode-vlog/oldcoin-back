package kr.co.oldcoinback.common.filter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.oldcoinback.common.enums.ResponseCode;
import kr.co.oldcoinback.common.exception.LimeException;
import kr.co.oldcoinback.common.token.JwtTokenProvider;
import kr.co.oldcoinback.common.utils.CookieUtils;
import kr.co.oldcoinback.config.properties.AllowUris;
import kr.co.oldcoinback.domain.response.user.CustomUserDetail;
import kr.co.oldcoinback.domain.response.user.ResolvedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;
    private final AllowUris allowUris;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, LimeException, IOException {
       boolean setCookieFlag = false;
       Optional<String> optionalToken = Optional.ofNullable(CookieUtils.getCookie("accessToken"));

       if(optionalToken.isEmpty()) {

            optionalToken = Optional.ofNullable(CookieUtils.getCookie("refreshToken"));
            setCookieFlag = true;
            if(optionalToken.isEmpty()) {
                throw new LimeException(ResponseCode.UNAUTHORIZED);
            }

        }
        String token = optionalToken.get();
        final ResolvedUser resolvedUser = setAuthenticate(token);
        doFilter(request, response, filterChain);

        if(setCookieFlag) {
            jwtTokenProvider.setResponseToken(resolvedUser, response);
        }

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        if(PathRequest.toStaticResources().atCommonLocations().matches(request)) {
            return true;
        }
        if( PatternMatchUtils.simpleMatch(allowUris.getAllowUris(), request.getRequestURI())) {
            return true;
        }


        return super.shouldNotFilter(request);
    }

    private ResolvedUser setAuthenticate(String token) {
        Map payload = jwtTokenProvider.validateToken(token);

        final UserDetails user = userDetailsService.loadUserByUsername((String) payload.get("userId"));
        final ResolvedUser resolvedUser = ResolvedUser.fromSecurity((CustomUserDetail) user);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(resolvedUser, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);

        return resolvedUser;

    }
}
