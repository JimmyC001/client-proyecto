package co.com.cattleya.auth.presentation.controller;

import co.com.cattleya.auth.application.dto.login.JwtLoginRequest;
import co.com.cattleya.auth.application.dto.login.JwtLoginResponse;
import co.com.cattleya.auth.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class LoginController {
    @Autowired
    private UserService service;
    @PostMapping("login")
    public ResponseEntity<JwtLoginResponse> execute(@RequestBody JwtLoginRequest request) throws Exception {
        return null;
    }
}
