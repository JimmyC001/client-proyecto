package co.com.cattleya.endpoint.user;

import co.com.cattleya.endpoint.user.dto.login.LoginRequest;
import co.com.cattleya.endpoint.user.dto.login.LoginResponse;
import co.com.cattleya.endpoint.user.dto.register.RegisterRequest;
import co.com.cattleya.endpoint.user.dto.register.RegisterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private RestTemplate userTemplate;
    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
    @Autowired
    public HttpHeaders getHeaders() {
        String credentials = "username:password";
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + encodedCredentials);
        return headers;
    }
    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
        if(request == null)
            return ResponseEntity.noContent().build();
        try{
            HttpEntity<LoginRequest> requestEntity = new HttpEntity<>(request, getHeaders());
            return ResponseEntity.ok(userTemplate.postForObject("http://users/user/login", requestEntity, LoginResponse.class));
        }catch(HttpClientErrorException | HttpServerErrorException ex){
            String message = ex.getMessage();
            HttpStatus status = (HttpStatus) ex.getStatusCode();
            return ResponseEntity.status(status).body(new LoginResponse(status.value(), message));
        }
    }
    @PostMapping("register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request){
        if(request == null)
            return ResponseEntity.noContent().build();
        try {
            HttpEntity<RegisterRequest> requestEntity = new HttpEntity<>(request, getHeaders());
            return ResponseEntity.ok(userTemplate.postForObject("http://users/user/register", requestEntity, RegisterResponse.class));
        } catch(HttpClientErrorException | HttpServerErrorException ex){
            ex.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
