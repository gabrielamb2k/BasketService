package dev.java.ecommerce.basket.service.controller;

import dev.java.ecommerce.basket.service.controller.request.BasketRequest;
import dev.java.ecommerce.basket.service.controller.request.PaymentRequest;
import dev.java.ecommerce.basket.service.entity.Basket;
import dev.java.ecommerce.basket.service.service.BasketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/basket")
@RequiredArgsConstructor
@Tag(name = "basket controller", description = "gerencia operacoes com as baskets")
public class BasketController {

    private final BasketService basketService;

    @GetMapping("/{id}")
    @Operation(summary = "Pega o produto por id", description = "Rota busca o basket pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "basket acessadsa com sucesso"),
            @ApiResponse(responseCode = "404", description = "basket nao encontrada")
    })
    public ResponseEntity<Basket> getBasketById(@PathVariable String id){
        return ResponseEntity.ok(basketService.getBasketById(id));
    }

    @PostMapping
    @Operation(summary = "cria uma basket", description = "Rota cria uma basket")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "basket criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "erro na criaçao da basket")
    })
    public ResponseEntity<Basket> createBasket(@RequestBody BasketRequest basketRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(basketService.createBasket(basketRequest));
    }


    @PutMapping("/{id}")
    @Operation(summary = "atualiza uma basket por um id",
            description = "Rota atualiza uma basket ja criada por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "basket atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "erro na atualizaçapo da basket"),
            @ApiResponse(responseCode = "404", description = "basket nao encontrada")
    })
    public ResponseEntity<Basket> updateBasket(@PathVariable String id ,@RequestBody BasketRequest basketRequest){
        return ResponseEntity.status(HttpStatus.OK).body(basketService.updateBasket(id, basketRequest));
    }

    @PutMapping("/{id}/payment")
    @Operation(summary = "pagamento da basket",
            description = "rota leva para a area de pagamento da basket")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "pagamento realizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "erro no pagamento da basket"),
            @ApiResponse(responseCode = "404", description = "basket nao encontrada")
    })
    public ResponseEntity<Basket> payBasket(@PathVariable String id ,@RequestBody PaymentRequest paymentRequest){
        return ResponseEntity.status(HttpStatus.OK).body(basketService.payBasket(id, paymentRequest));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "deleta uma basket",
            description = "rota deleta uma basket")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "basket deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "basket nao encontrada"),
            @ApiResponse(responseCode = "500", description = "erro ao deletar uma basket")
    })
    public ResponseEntity<Void> deleteBasket(@PathVariable String id){
        basketService.deleteBasket(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
