package nl.fontys.s3.comfyshop.controller;

import lombok.AllArgsConstructor;
import nl.fontys.s3.comfyshop.bussiness.exception.InvalidShoppingCartException;
import nl.fontys.s3.comfyshop.bussiness.shoppingCart.UpdateShoppingSessionUC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/shopping_session")
@AllArgsConstructor
public class CartShoppingController {

    private final UpdateShoppingSessionUC updateShoppingSessionUC;

    @PostMapping("/{shoppingSessionId}")
    public ResponseEntity<String> updateShoppingSession(@PathVariable Long shoppingSessionId) {
        try {
            boolean updated = updateShoppingSessionUC.UpdateShoppingSession(shoppingSessionId);
            if (updated) {
                return ResponseEntity.ok("Shopping session updated successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update shopping session.");
            }
        } catch (InvalidShoppingCartException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid shopping session ID.");
        }
    }

}
