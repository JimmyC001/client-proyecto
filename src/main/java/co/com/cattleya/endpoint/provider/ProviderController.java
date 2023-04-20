package co.com.cattleya.endpoint.provider;

import co.com.cattleya.endpoint.provider.dto.ProviderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("provider")
public class ProviderController {
    @Autowired
    RestTemplate providerTemplate;
    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
    @GetMapping
    public ResponseEntity<ProviderResponse> getAllProviders(){
        return ResponseEntity.ok(providerTemplate.getForObject("http;//providers/provider", ProviderResponse.class));
    }
}
