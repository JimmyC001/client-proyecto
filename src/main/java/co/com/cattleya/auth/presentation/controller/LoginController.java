package co.com.cattleya.auth.presentation.controller;

import co.com.cattleya.auth.application.dto.login.JwtLoginRequest;
import co.com.cattleya.auth.application.dto.login.JwtLoginResponse;
import co.com.cattleya.auth.domain.model.User;
import co.com.cattleya.auth.domain.service.UserService;
import co.com.cattleya.auth.infrastructure.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class LoginController {
    @Autowired
    private JwtUtil jwtUtil;
    private final AuthenticationManager manager;
    private final UserService service;
    @PostMapping("login")
    public ResponseEntity<JwtLoginResponse> execute(@RequestBody JwtLoginRequest request) {
        try {
            manager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
            ));
        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
        User user = service.findUser(request.getUsername());
        if(user == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new JwtLoginResponse(jwtUtil.generateToken(user)));
    }
}
