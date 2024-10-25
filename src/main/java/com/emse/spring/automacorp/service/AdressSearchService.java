package com.emse.spring.automacorp.service;

import com.emse.spring.automacorp.dto.ApiGouvAdress;
import com.emse.spring.automacorp.dto.ApiGouvResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class AdressSearchService {

    private final RestTemplate restTemplate;

    public AdressSearchService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<ApiGouvAdress> searchAdress(List<String> keys) {
        String params = String.join("+", keys);
        String uri = UriComponentsBuilder
                .fromUriString("/search")
                .queryParam("q", params)
                .queryParam("limit", 15)
                .build()
                .toUriString();

        ApiGouvResponse response = restTemplate.getForObject(uri, ApiGouvResponse.class);
        return response != null ? response.features().stream().map(f -> f.properties()).toList() : List.of();
    }
}
