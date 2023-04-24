package co.com.cattleya.auth.application.mapper;

import co.com.cattleya.auth.application.dto.login.JwtLoginRequest;
import co.com.cattleya.auth.domain.model.User;

public class LoginMapper {
    public static User toUser(JwtLoginRequest request){
        return User.builder()
            .username(request.getUsername())
            .password(request.getPassword())
        .build();
    }
}
