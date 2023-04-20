package co.com.cattleya.endpoint.provider.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProviderResponse {
    private String name;
    private String photo;
    private String description;
    private String web;
}
