package com.poku.graypants.domain.cart.web;

import com.poku.graypants.domain.cart.application.CartService;
import com.poku.graypants.domain.cart.application.dto.AddCartItemRequestDto;
import com.poku.graypants.domain.cart.persistence.CartItem;
import com.poku.graypants.domain.cart.application.dto.UpdateCartItemQuantityRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/items")
    public ResponseEntity<CartItem> addItemToCart(
            @RequestBody AddCartItemRequestDto request,
            @RequestParam String email) {
        CartItem cartItem = cartService.addItemToCart(request.getItemId(), request.getQuantity(), email);
        return ResponseEntity.ok(cartItem);
    }

    @GetMapping("/items")
    public ResponseEntity<List<CartItem>> getCartItems(
            @RequestParam String email) {
        List<CartItem> cartItems = cartService.getCartItems(email);
        return ResponseEntity.ok(cartItems);
    }

    @DeleteMapping("/items/{cartitem_id}")
    public ResponseEntity<Void> removeItemFromCart(
            @PathVariable Long cartitem_id,
            @RequestParam String email) {
        cartService.removeItemFromCart(cartitem_id, email);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/items/{cartitem_id}")
    public ResponseEntity<Void> updateCartItemQuantity(
            @RequestBody UpdateCartItemQuantityRequestDto request,
            @RequestParam String email) {
        cartService.updateCartItemQuantity(request.getCartItemId(), request.getNewQuantity(), email);
        return ResponseEntity.ok().build();
    }
}

