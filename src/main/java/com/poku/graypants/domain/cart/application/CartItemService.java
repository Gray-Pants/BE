package com.poku.graypants.domain.cart.application;

import com.poku.graypants.domain.cart.persistence.CartItem;
import com.poku.graypants.domain.cart.persistence.CartItemRepository;
import com.poku.graypants.domain.item.persistence.Item;
import com.poku.graypants.domain.user.persistence.User;
import com.poku.graypants.global.exception.ExceptionStatus;
import com.poku.graypants.global.exception.GrayPantsException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepository;


    public List<CartItem> getCartItemsByUserId(Long principal) {
        return cartItemRepository.findByUser_UserId(principal);
    }

    private CartItem getVerityCartItemById(Long cartItemId) {
        return cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new GrayPantsException(ExceptionStatus.CART_ITEM_NOT_FOUND));
    }

    public CartItem createCartItem(int quantity, User user, Item item) {
        return cartItemRepository.save(new CartItem(quantity, user, item));
    }

    public void deleteCartItem(Long cartItemId, Long principal) {
        CartItem cartItem = getVerityCartItemById(cartItemId);
        if (!cartItem.getUser().getUserId().equals(principal)) {
            throw new GrayPantsException(ExceptionStatus.CART_ITEM_USER_MISMATCH);
        }
        cartItemRepository.delete(cartItem);
    }

}
