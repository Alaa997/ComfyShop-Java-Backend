package nl.fontys.s3.comfyshop.controller;

import lombok.AllArgsConstructor;
import nl.fontys.s3.comfyshop.bussiness.order.CreateOrderUC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {
    private final CreateOrderUC createOrderUC;

    @PostMapping("/{userId}")
    public ResponseEntity<Void> createOrder(@PathVariable Long userId) {
        createOrderUC.createOrder(userId);
        return ResponseEntity.ok().build();
    }
}
