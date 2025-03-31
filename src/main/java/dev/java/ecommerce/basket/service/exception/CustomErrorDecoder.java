package dev.java.ecommerce.basket.service.exception;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()){
            case 400:
                return new DataNotFoundException("Product not found");
            default:
                return new Exception("Excelption ehile getting producut");
        }
    }
}
