package com.poku.graypants.domain.cart.web;

import static com.poku.graypants.global.util.ApiResponseUtil.success;

import com.poku.graypants.domain.cart.application.CartService;
import com.poku.graypants.domain.cart.application.dto.CartItemAddRequestDto;
import com.poku.graypants.domain.cart.application.dto.CartItemResponseDto;
import com.poku.graypants.domain.cart.application.dto.CartItemUpdateRequestDto;
import com.poku.graypants.global.util.ApiResponseUtil.ApiResult;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/myitems")
    public ResponseEntity<ApiResult<List<CartItemResponseDto>>> getMyCartItems(
            Authentication authentication) {
        List<CartItemResponseDto> response = cartService.getMyCartItems((Long) authentication.getPrincipal());
        return new ResponseEntity<>(success(response), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/items")
    public ResponseEntity<ApiResult<String>> createCartItem(
            @RequestBody CartItemAddRequestDto request,
            Authentication authentication) {
        cartService.createCartItem(request, (Long) authentication.getPrincipal());
        return new ResponseEntity<>(success("장바구니에 상품이 추가되었습니다."), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/items")
    public ResponseEntity<ApiResult<List<CartItemResponseDto>>> updateCartItem(
            @RequestBody CartItemUpdateRequestDto request,
            Authentication authentication) {
        List<CartItemResponseDto> response = cartService.updateCartItem(request, (Long) authentication.getPrincipal());
        return new ResponseEntity<>(success(response), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/items/{cartItemId}")
    public ResponseEntity<ApiResult<List<CartItemResponseDto>>> deleteCartItem(@PathVariable Long cartItemId,
            Authentication authentication) {
        List<CartItemResponseDto> response = cartService.deleteCartItem(cartItemId, (Long) authentication.getPrincipal());
        return new ResponseEntity<>(success(response), HttpStatus.OK);
    }
}

