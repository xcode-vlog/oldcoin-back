package kr.co.oldcoinback.common.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.oldcoinback.common.enums.TokenType;
import kr.co.oldcoinback.common.utils.CookieUtils;
import kr.co.oldcoinback.domain.response.user.ResolvedUser;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

@Slf4j
public class JwtTokenProvider {

    public JwtTokenProperties jwtTokenProperties;

    public JwtTokenProvider(JwtTokenProperties jwtTokenProperties) {
        this.jwtTokenProperties = jwtTokenProperties;
    }

    public LocalDateTime getExpires(TokenType type) {
        LocalDateTime now = LocalDateTime.now();
        if(type == TokenType.ACCESS_TOKEN) {
            return now.plusSeconds(jwtTokenProperties.getAccessExpiredSeconds());
        } else if(type == TokenType.REFRESH_TOKEN) {
            return now.plusSeconds(jwtTokenProperties.getRefreshExpiredSeconds());
        } else {
            return null;
        }
    }

    public String generateToken(Map<String, Object> payload) {
        return Jwts.builder()
                .signWith(getSecretKey())
                .claims(payload)
                .compact();
    }
    public String generateToken(Map<String, Object> payload, LocalDateTime expires) {

        return Jwts.builder()
                .signWith(getSecretKey())
                .claims(payload)
                .expiration(Date.from(expires.atZone(ZoneId.systemDefault()).toInstant()))
                .compact();
    }

    public Map validateToken(String token) {
        final Claims payload = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return payload;
    }


    public ResolvedUser getUserFromToken(String token) {
        final Map map = validateToken(token);
        return ResolvedUser.builder()
                .userSeq(map.get("userSeq").toString())
                .userId(map.get("userId").toString())
                .name(map.get("name").toString())
                .nickName(map.get("nickName").toString())
                .email(map.get("email").toString())
                .userRole(map.get("userRole").toString())
                .build();
    }

    private SecretKey getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtTokenProperties.getSecret());
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public void setCookie(TokenType type, HttpServletResponse response, String token) {
        if(type == TokenType.ACCESS_TOKEN) {
            CookieUtils.setCookie(response, "accessToken", token, jwtTokenProperties.getAccessExpiredSeconds(), false);
        } else if(type == TokenType.REFRESH_TOKEN) {
            CookieUtils.setCookie(response, "refreshToken", token, jwtTokenProperties.getRefreshExpiredSeconds(), true);
        } else {
            CookieUtils.setCookie(response, "accessToken", "", 0L, false);
            CookieUtils.setCookie(response, "refreshToken", "", 0L, true);
        }
    }

    public void setResponseToken(ResolvedUser user, HttpServletResponse response) {
        final LocalDateTime accessTokenExpires = getExpires(TokenType.ACCESS_TOKEN);
        final LocalDateTime refreshTokenExpires = getExpires(TokenType.REFRESH_TOKEN);

        Map<String, Object> payload = user.toMap();

        String accessToken = generateToken(payload, accessTokenExpires);
        String refreshToken = generateToken(payload, refreshTokenExpires);

        log.info(accessToken);

        setCookie(TokenType.ACCESS_TOKEN, response, accessToken);
        setCookie(TokenType.REFRESH_TOKEN, response, refreshToken);
    }

    public void removeResponseToken(HttpServletResponse response) {
        setCookie(null, response, null);

    }
}
