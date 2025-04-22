package kr.co.oldcoinback.common.token;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JwtTokenProperties {
    @Setter
    private String secret;
    private Long accessExpiredSeconds;
    private Long refreshExpiredSeconds;

    public void setAccessExpiredSeconds(String accessExpiredSeconds) {
        this.accessExpiredSeconds = Long.parseLong(accessExpiredSeconds);
    }
    public void setRefreshExpiredSeconds(String refreshExpiredSeconds) {
        this.refreshExpiredSeconds = Long.parseLong(refreshExpiredSeconds);
    }
}
