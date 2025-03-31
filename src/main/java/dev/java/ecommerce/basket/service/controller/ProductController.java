package dev.java.ecommerce.basket.service.controller;

import dev.java.ecommerce.basket.service.Client.response.PlatziProductResponse;
import dev.java.ecommerce.basket.service.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @GetMapping
    @Operation(summary = "pega todos os produtos na API", description = "rota pega dos dados da api" +
            "e insere no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "produtos encontrados"),
            @ApiResponse(responseCode = "400", description = "erro ao pegar produtos na api")
    })
    public ResponseEntity<List<PlatziProductResponse>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlatziProductResponse> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }
}
