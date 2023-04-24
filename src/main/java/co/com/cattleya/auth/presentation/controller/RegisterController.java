package co.com.cattleya.auth.presentation.controller;

import co.com.cattleya.auth.application.dto.register.RegisterRequest;
import co.com.cattleya.auth.application.dto.register.RegisterResponse;
import co.com.cattleya.auth.application.mapper.RegisterMapper;
import co.com.cattleya.auth.domain.model.User;
import co.com.cattleya.auth.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class RegisterController {
    private final UserService service;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    RegisterController(UserService service){
        this.service = service;
    }
    @PostMapping("register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request){
        if(request == null)
            return ResponseEntity.badRequest().build();
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        User savedUser = service.saveUser(RegisterMapper.toUser(request));
        if(savedUser == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(RegisterMapper.toResponse(savedUser));
    }
}
