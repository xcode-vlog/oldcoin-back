package kr.co.oldcoinback.config;

import kr.co.oldcoinback.common.token.JwtTokenProperties;
import kr.co.oldcoinback.common.token.JwtTokenProvider;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtTokenProviderConfig {

    @Bean
    @ConfigurationProperties(prefix = "jwt")
    public JwtTokenProperties jwtTokenProperties() {
        return new JwtTokenProperties();
    }

    @Bean
    public JwtTokenProvider jwtTokenProvider(JwtTokenProperties jwtTokenProperties) {
        return new JwtTokenProvider(jwtTokenProperties);
    }

}
