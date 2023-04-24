package co.com.cattleya.auth.application.mapper;

import co.com.cattleya.auth.application.dto.register.RegisterRequest;
import co.com.cattleya.auth.application.dto.register.RegisterResponse;
import co.com.cattleya.auth.domain.model.User;
import co.com.cattleya.auth.domain.model.UserRole;

public class RegisterMapper {
    public static User toUser(RegisterRequest request){
        return User.builder()
            .username(request.getUsername())
            .password(request.getPassword())
            .role(UserRole.getFromString(request.getRole().toUpperCase()))
        .build();
    }
    public static RegisterResponse toResponse(User user){
        return RegisterResponse.builder()
            .username(user.getUsername())
            .role(user.getRole().getName())
        .build();
    }
}
