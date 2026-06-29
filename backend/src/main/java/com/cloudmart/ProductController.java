package com.cloudmart;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ProductController {

    @GetMapping("/health")
    public Map<String, String> health() {
        // Implementation for fetching health information
        return Map.of("status", "UP");
    }

    @GetMapping("/products")
    public List<Map<String, Object>> getProducts() {
        // Implementation for fetching products
        return List.of(
            Map.of("id", 1, "name", "Product 1", "price", 10.99),
            Map.of("id", 2, "name", "Product 2", "price", 15.99),
            Map.of("id", 3, "name", "Product 3", "price", 20.99)
        );
    }

}