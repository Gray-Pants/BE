import com.poku.graypants.domain.cart.application.CartService;
import com.poku.graypants.domain.cart.persistence.Cart;
import com.poku.graypants.domain.cart.persistence.CartItem;
import com.poku.graypants.domain.cart.application.dto.AddCartItemRequest;
import com.poku.graypants.domain.cart.application.dto.UpdateCartItemQuantityRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<Cart> createCart() {
        Cart cart = cartService.createCart();
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/{cartId}/items")
    public ResponseEntity<CartItem> addItemToCart(
            @PathVariable Long cartId,
            @RequestBody AddCartItemRequest request) {
        CartItem cartItem = cartService.addItemToCart(cartId, request.getItemId(), request.getQuantity());
        return ResponseEntity.ok(cartItem);
    }

    @GetMapping("/{cartId}/items")
    public ResponseEntity<List<CartItem>> getCartItems(@PathVariable Long cartId) {
        List<CartItem> cartItems = cartService.getCartItems(cartId);
        return ResponseEntity.ok(cartItems);
    }

    @DeleteMapping("/items/{cartItemId}")
    public ResponseEntity<Void> removeItemFromCart(@PathVariable Long cartItemId) {
        cartService.removeItemFromCart(cartItemId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/items/{cartItemId}")
    public ResponseEntity<Void> updateCartItemQuantity(
            @PathVariable Long cartItemId,
            @RequestBody UpdateCartItemQuantityRequest request) {
        cartService.updateCartItemQuantity(cartItemId, request.getNewQuantity());
        return ResponseEntity.ok().build();
    }
}

