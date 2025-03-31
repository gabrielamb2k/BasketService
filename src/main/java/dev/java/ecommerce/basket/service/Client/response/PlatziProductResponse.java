package dev.java.ecommerce.basket.service.Client.response;

import java.io.Serializable;
import java.math.BigDecimal;

public record PlatziProductResponse(
        Long id,
        String title,
        BigDecimal price
) implements Serializable {
}
