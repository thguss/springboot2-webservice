package com.thguss.book.springboot.config.auth.dto;

import com.thguss.book.springboot.domain.user.Role;
import com.thguss.book.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {

    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    // 사용자 정보는 Map이므로 값 하나하나씩 반환
    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes).nameAttributeKey(userNameAttributeName).build();
    }

    // User entity 생성 | 기본 권한을 GUEST로 주기 위해서
    public User toEntity() {
        return User.builder().name(name).email(email).picture(picture).role(Role.GUEST).build();
    }
}
