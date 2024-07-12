package com.poku.graypants.domain.cart.application;

import com.poku.graypants.domain.cart.persistence.CartItem;
import com.poku.graypants.domain.cart.persistence.CartItemRepository;
import com.poku.graypants.domain.cart.persistence.CartRepository;
import com.poku.graypants.domain.item.application.ItemService;
import com.poku.graypants.domain.item.persistence.Item;
import com.poku.graypants.domain.user.application.UserService;
import com.poku.graypants.global.exception.ExceptionStatus;
import com.poku.graypants.global.exception.GrayPantsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserService userService;
    private final ItemService itemService;

    public CartItem addItemToCart(Long item_id, int quantity, String email) {
        userService.getUserByEmail(email);
        Item item = itemService.getItemById(item_id);
//        CartItem cartItem =  CartItem.builder()
//                .cart(myCart)
//                .item(item)
//                .cartItemQuantity(quantity)
//                .build();
//        return cartItemRepository.save(cartItem);
        return null;
    }

    @Transactional(readOnly = true)
    public List<CartItem> getCartItems(String email) {
//        Cart cart = userService.getCartByEmail(email);
//        return cartItemRepository.findByCart(cart);
        return null;
    }

    public void removeItemFromCart(Long cartItem_id, String email) {
        Optional<CartItem> cartItem = cartItemRepository.findById(cartItem_id);
        if (cartItem.isEmpty()) {
            throw new GrayPantsException(ExceptionStatus.ITEM_NOT_FOUND);
        }
        if(cartItem.get().getCart().getUser().getEmail().equals(email)) {
            cartItemRepository.deleteById(cartItem_id);
        };
    }

    public void updateCartItemQuantity(Long cartItem_id, int quantity, String email) {
        Optional<CartItem> cartItem = cartItemRepository.findById(cartItem_id);
        if (cartItem.isEmpty()) {
            throw new GrayPantsException(ExceptionStatus.ITEM_NOT_FOUND);
        }
        if(cartItem.get().getCart().getUser().getEmail().equals(email)) {
            if (quantity <= 0) {
                throw new GrayPantsException(ExceptionStatus.INVALID_QUANTITY);
            }
            cartItem.get().updateQuantity(quantity);
        };
    }
}
