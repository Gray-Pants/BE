package com.poku.graypants.domain.cart.application;

import com.poku.graypants.domain.cart.application.dto.CartItemAddRequestDto;
import com.poku.graypants.domain.cart.application.dto.CartItemResponseDto;
import com.poku.graypants.domain.cart.application.dto.CartItemUpdateRequestDto;
import com.poku.graypants.domain.cart.persistence.CartItem;
import com.poku.graypants.domain.item.application.ItemService;
import com.poku.graypants.domain.item.persistence.Item;
import com.poku.graypants.domain.user.application.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {

    private final CartItemService cartItemService;
    private final UserService userService;
    private final ItemService itemService;


    public List<CartItemResponseDto> getMyCartItems(Long principal) {
        List<CartItem> cartItems = cartItemService.getCartItemsByUserId(principal);
        return cartItems.stream().map(CartItemResponseDto::new).toList();
    }

    public void createCartItem(CartItemAddRequestDto request, Long UserId) {
        Item item = itemService.getVerifyItemById(request.getItemId());
        cartItemService.createCartItem(request.getQuantity(), userService.getVerifyUserByUserId(UserId), item);
    }

    public List<CartItemResponseDto> updateCartItem(CartItemUpdateRequestDto request, Long principal) {
        List<CartItem> cartItems = cartItemService.getCartItemsByUserId(principal);
        cartItems.forEach(cartItem -> {
            if (cartItem.getCartItemId().equals(request.getCartItemId())) {
                cartItem.updateQuantity(request.getNewQuantity());
            }
        });
        return cartItems.stream().map(CartItemResponseDto::new).toList();
    }

    public List<CartItemResponseDto> deleteCartItem(Long cartItemId, Long principal) {
        cartItemService.deleteCartItem(cartItemId, principal);
        return getMyCartItems(principal);
    }
}

