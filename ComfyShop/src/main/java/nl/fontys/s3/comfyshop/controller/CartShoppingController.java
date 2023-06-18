package nl.fontys.s3.comfyshop.controller;

import lombok.AllArgsConstructor;
import nl.fontys.s3.comfyshop.bussiness.exception.InvalidShoppingCartException;
import nl.fontys.s3.comfyshop.bussiness.shoppingCart.GetOrdersUC;
import nl.fontys.s3.comfyshop.bussiness.shoppingCart.GetSessionIdUC;
import nl.fontys.s3.comfyshop.bussiness.shoppingCart.UpdateShoppingSessionUC;
import nl.fontys.s3.comfyshop.configuration.security.isauthenticated.IsAuthenticated;
import nl.fontys.s3.comfyshop.dto.shopping.ShoppingSessionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/shopping_session")
@AllArgsConstructor
@IsAuthenticated
@RolesAllowed({"ROLE_CUSTOMER"})
public class CartShoppingController {

    private final UpdateShoppingSessionUC updateShoppingSessionUC;
    private final GetOrdersUC getOrdersUC;
    private final GetSessionIdUC getShoppingSession;

    @GetMapping("/{userId}")
    public ResponseEntity<Long> getSessionId(@PathVariable("userId") Long userId) {
        Long sessionId = getShoppingSession.GetSessionId(userId);
        return ResponseEntity.ok(sessionId);
    }

    @PutMapping("{shoppingSessionId}/{userId}") // Place order by changing status ordered to true
    public ResponseEntity<String> updateShoppingSession(@PathVariable Long shoppingSessionId, @PathVariable Long userId) {
        try {
            boolean updated = updateShoppingSessionUC.UpdateShoppingSession(shoppingSessionId, userId);
            if (updated) {
                return ResponseEntity.ok("Shopping session updated successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update shopping session.");
            }
        } catch (InvalidShoppingCartException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid shopping session ID.");
        }
    }


    @GetMapping("/orders/{userId}")
    public ResponseEntity<List<ShoppingSessionDTO>> getOrders(@PathVariable("userId") Long userId) {
        List<ShoppingSessionDTO> orders = getOrdersUC.getOrders(userId);
        return ResponseEntity.ok(orders);
    }
}
