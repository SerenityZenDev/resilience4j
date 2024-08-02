package com.spring_cloud.resilience4j.sample;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.logging.Logger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final Logger log = Logger.getLogger(this.getClass().getName());

    @CircuitBreaker(name = "productService", fallbackMethod = "fallbackGetProductDetails")
    public Product getProductDetails(String productId) {
        if ("111".equals(productId)) {
            throw new RuntimeException("Empty response body");
        }
        return new Product(
            productId,
            "Sample Product" + productId
        );
    }

    public Product fallbackGetProductDetails(String productId, Throwable t) {
        return new Product(
            "0",
            "Fallback Product" + productId
        );
    }

}
