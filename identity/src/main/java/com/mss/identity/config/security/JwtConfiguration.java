package com.mss.identity.config.security;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtConfiguration {
    private String secret;
    private int accessDuration;
    private int refreshableDuration;
    private String issuer;
    private List<String> aud;

    public long getAccessDurationInMillis() {
        return TimeUnit.MINUTES.toMillis(accessDuration);
    }

    public long getRefreshableDurationInMillis() {
        return TimeUnit.MINUTES.toMillis(refreshableDuration);
    }
}
