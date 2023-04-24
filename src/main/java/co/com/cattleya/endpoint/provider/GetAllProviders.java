package co.com.cattleya.endpoint.provider;

import co.com.cattleya.endpoint.provider.dto.GetProviderResponse;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("provider")
public class GetAllProviders {
    private RestTemplate template;
    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
    @GetMapping
    public ResponseEntity<GetProviderResponse> getAllProviders(){
        return ResponseEntity.ok(new GetProviderResponse("SIUU"));
    }
}
