package com.emse.spring.automacorp.api;

import com.emse.spring.automacorp.dto.ApiGouvAdress;
import com.emse.spring.automacorp.service.AdressSearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdressController {

    private final AdressSearchService adressSearchService;

    public AdressController(AdressSearchService adressSearchService) {
        this.adressSearchService = adressSearchService;
    }

    @GetMapping("/search-address")
    public List<ApiGouvAdress> searchAddress(@RequestParam List<String> keys) {
        return adressSearchService.searchAdress(keys);
    }
}
