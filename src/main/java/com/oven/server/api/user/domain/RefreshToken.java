package com.oven.server.api.user.domain;

import com.oven.server.common.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@RedisHash(value = "refreshToken", timeToLive = 60 * 60 * 24 * 14) // 단위는 초
public class RefreshToken extends BaseEntity {

    @Id
    @Indexed
    private String refreshToken;
    private String username;

    @Builder
    public RefreshToken(String refreshToken, String username) {
        this.refreshToken = refreshToken;
        this.username = username;
    }

}
