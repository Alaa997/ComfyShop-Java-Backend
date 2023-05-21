package nl.fontys.s3.comfyshop.controller;

import lombok.AllArgsConstructor;
import nl.fontys.s3.comfyshop.bussiness.cartItem.AddToCartUC;
import nl.fontys.s3.comfyshop.bussiness.cartItem.GetCartItemsUC;
import nl.fontys.s3.comfyshop.bussiness.cartItem.RemoveCartItemUC;
import nl.fontys.s3.comfyshop.bussiness.cartItem.UpdateCartItemUC;
import nl.fontys.s3.comfyshop.dto.shopping.CartItemDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/cart")
@AllArgsConstructor
public class CartItemController {
    private final AddToCartUC addToCartUC;
    private final GetCartItemsUC getCartItemsUC;
    private final RemoveCartItemUC removeCartItemUC;
    private final UpdateCartItemUC updateCartItemUC;

    @PostMapping("/add")
    public ResponseEntity<?> addToCart(@RequestBody CartItemDTO request) {
        boolean added = addToCartUC.addToCart(request);
        if (added) {
            return ResponseEntity.ok("Item added to cart successfully.");
        } else {
            return ResponseEntity.badRequest().body("Failed to add item to cart.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<CartItemDTO>> getCartItems(@PathVariable("id") Long id) {
        List<CartItemDTO> cartItems = getCartItemsUC.getCartItems(id);
        return ResponseEntity.ok(cartItems);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeCartItem(@PathVariable("id") Long id) {
        boolean removed = removeCartItemUC.removeCartItem(id);
        if (removed) {
            return ResponseEntity.ok("Item removed from cart successfully.");
        } else {
            return ResponseEntity.badRequest().body("Failed to remove item from cart.");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCartItem(@RequestBody CartItemDTO request) {
        boolean updated = updateCartItemUC.updateCartItem(request);
        if (updated) {
            return ResponseEntity.ok("Item updated in cart successfully.");
        } else {
            return ResponseEntity.badRequest().body("Failed to update item in cart.");
        }
    }
}
