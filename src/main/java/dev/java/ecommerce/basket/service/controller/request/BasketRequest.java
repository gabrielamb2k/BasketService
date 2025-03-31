package dev.java.ecommerce.basket.service.controller.request;

import java.util.List;

public record BasketRequest(
        Long clientId,
        List<ProductRequest> products
) {
}
