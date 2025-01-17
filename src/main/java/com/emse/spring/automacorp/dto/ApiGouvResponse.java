package com.emse.spring.automacorp.dto;

import java.util.List;

public record ApiGouvResponse(
        String version,
        String query,
        Integer limit,
        List<ApiGouvFeature> features
) {
}
