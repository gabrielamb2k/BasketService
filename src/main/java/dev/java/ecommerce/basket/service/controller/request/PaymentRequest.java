package dev.java.ecommerce.basket.service.controller.request;

import dev.java.ecommerce.basket.service.entity.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {
    private PaymentMethod paymentMethod;
}
