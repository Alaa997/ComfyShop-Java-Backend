package nl.fontys.s3.comfyshop.controller;

import lombok.AllArgsConstructor;
import nl.fontys.s3.comfyshop.bussiness.product.*;
import nl.fontys.s3.comfyshop.dto.ProductDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductsController {
    private final CreateProductUC createProductUC;
    private final UpdateProductUC updateProductUC;
    private final GetProductsByCategoryUC getProductsByCategoryUC;
    private final GetProductUC getProductUC;
    private final GetAllProductsUC getAllProductsUC;
    private final nl.fontys.s3.comfyshop.bussiness.product.DeleteProductUC deleteProductUC;

    @GetMapping("/product/{id}")
    public final ResponseEntity<ProductDTO> getProduct(@PathVariable(value = "id") final long id) {
        final Optional<ProductDTO> productOptional = getProductUC.getProduct(id);
        if (productOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(productOptional.get());
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

    @PostMapping()
    public ResponseEntity<ProductDTO> createProduct(@RequestBody @Valid ProductDTO request) {
        ProductDTO response = createProductUC.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable("id") long id, @RequestBody @Valid ProductDTO request) {
        request.setId(id);
        updateProductUC.updateProduct(request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long productId) {
        deleteProductUC.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
}
