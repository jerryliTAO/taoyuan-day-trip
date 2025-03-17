package jerryli.taoyuandaytrip.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jerryli.taoyuandaytrip.pojo.*;
import jerryli.taoyuandaytrip.pojo.request.CartItemRequest;
import jerryli.taoyuandaytrip.service.CartService;
import jerryli.taoyuandaytrip.service.impl.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jerry
 * @create 2024-05-29-下午 02:35
 */
@Tag(name="Cart")
@RestController
@Transactional
public class CartController {
    @Autowired
    private CartService cartService;


    @Operation(summary = "Get cart items by  user's ID")
    @GetMapping ("/api/cart/{uid}")
    public Cart getCart(@PathVariable("uid")Integer uid){
        Cart cart = cartService.getCart(uid);

        return cart;
    }



    @Operation(summary = "Add a new attraction itinerary to the cart")
    @PostMapping("/api/cart")
    public ResponseEntity<StatusResponse> addCartItem(@RequestBody CartItemRequest cartItemRequest) {
        Attraction attraction = Attraction.builder().id(cartItemRequest.getAttractionId()).build();
        User user = User.builder().id(cartItemRequest.getUserId()).build();

        CartItem cartItem = CartItem.builder()
                .user(user)
                .attraction(attraction)
                .date(cartItemRequest.getDateTime())
                .price(cartItemRequest.getPrice())
                .period(cartItemRequest.getPeriod())
                .build();

        int result = cartService.addCartItem(cartItem);
        if(result == 1){
            return ResponseEntity.ok(new StatusResponse("success"));
        }else{
            return ResponseEntity.ok(new StatusResponse("error"));
        }
    }

    @Operation(summary = "Delete the attraction itinerary by cart item ID")
    @DeleteMapping("/api/cart/{cartItemId}")
    public ResponseEntity<StatusResponse> deleteCartItem(@PathVariable("cartItemId")Integer cartItemId){
        int result = cartService.deleteCartItem(cartItemId);
        if(result == 1){
            return ResponseEntity.ok(new StatusResponse("success"));
        }else{
            return ResponseEntity.ok(new StatusResponse("error"));
        }
    }
}
