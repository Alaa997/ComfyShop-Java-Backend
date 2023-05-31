package nl.fontys.s3.comfyshop.controller;

import lombok.RequiredArgsConstructor;
import nl.fontys.s3.comfyshop.bussiness.product.*;
import nl.fontys.s3.comfyshop.configuration.security.isauthenticated.IsAuthenticated;
import nl.fontys.s3.comfyshop.dto.ProductDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductsController {
    private final CreateProductUC createProductUC;
    private final UpdateProductUC updateProductUC;
    private final GetProductsByCategoryUC getProductsByCategoryUC;
    private final GetProductUC getProductUC;
    private final GetAllProductsUC getAllProductsUC;
    private final DeleteProductUC deleteProductUC;

    //    @GetMapping("/product")
//    public ResponseEntity<ProductDTO> getProduct(@RequestParam(value = "id", required = false) Long id) {
//        final Optional<ProductDTO> productOptional = getProductUC.getProduct(id);
//        return productOptional.map(productDTO -> ResponseEntity.ok().body(productDTO)).orElseGet(() -> ResponseEntity.notFound().build());
//    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Long id) {
        if (id != null) {
            final Optional<ProductDTO> productOptional = getProductUC.getProduct(id);
            return productOptional.map(productDTO -> ResponseEntity.ok().body(productDTO)).orElseGet(() -> ResponseEntity.notFound().build());
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("")
    public ResponseEntity<List<ProductDTO>> getProducts(@RequestParam(value = "categoryId", required = false) Long categoryId) {
        List<ProductDTO> products;
        if (categoryId != null) {
            products = getProductsByCategoryUC.getProductsByCategoryId(categoryId);
        } else {
            products = getAllProductsUC.getAllProducts();
        }
        return ResponseEntity.ok(products);
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    @PostMapping()
    public ResponseEntity<ProductDTO> createProduct(@RequestBody @Valid ProductDTO request) {
        ProductDTO response = createProductUC.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //    @IsAuthenticated
//    @RolesAllowed({"ROLE_ADMIN"})
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable("id") long id, @RequestBody @Valid ProductDTO request) {
        request.setId(id);
        updateProductUC.updateProduct(request);
        return ResponseEntity.noContent().build();
    }

    //    @IsAuthenticated
//    @RolesAllowed({"ROLE_ADMIN"})
    @DeleteMapping("{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long productId) {
        deleteProductUC.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
}
