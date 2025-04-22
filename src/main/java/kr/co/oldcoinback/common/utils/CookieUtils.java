package kr.co.oldcoinback.common.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Arrays;
import java.util.Optional;

public class CookieUtils {
    private CookieUtils() {}

    public static void setCookie(HttpServletResponse response, String name, String value, boolean secure) {
        Cookie cookie = new Cookie(name, value);
        cookie.setDomain("localhost");
        cookie.setMaxAge(60*60*24);
        cookie.setPath("/");

        if(secure) cookie.setSecure(true);
        response.addCookie(cookie);
    }
    public static void setCookie(HttpServletResponse response, String name, String value, Long expires, boolean secure) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(expires.intValue());
        cookie.setPath("/");

        if(secure) cookie.setSecure(true);
        response.addCookie(cookie);
    }
    public static String getCookie(String name) {
        HttpServletRequest request = RequestContextUtils.getRequest();
        Cookie[] cookies = request.getCookies();
        if(cookies == null) {
            return null;
        }

        Optional<Cookie> cookie = Arrays.stream(cookies).filter((c) -> c.getName().equals(name)).findFirst();
        return cookie.map(Cookie::getValue).orElse(null);
    }
}
