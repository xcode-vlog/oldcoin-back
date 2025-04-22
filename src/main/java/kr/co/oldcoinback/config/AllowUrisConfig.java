package kr.co.oldcoinback.config;

import kr.co.oldcoinback.config.properties.AllowUris;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AllowUrisConfig {

    @Bean
    @ConfigurationProperties(prefix = "lime.allow-uris")
    public AllowUris allowUris() {
        return new AllowUris();
    }
}
