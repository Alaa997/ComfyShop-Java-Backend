package nl.fontys.s3.comfyshop.controller;

import lombok.AllArgsConstructor;
import nl.fontys.s3.comfyshop.bussiness.Product.CreateProductUC;
import nl.fontys.s3.comfyshop.bussiness.Product.UpdateProductUC;
import nl.fontys.s3.comfyshop.domain.Product.CreateProductRequest;
import nl.fontys.s3.comfyshop.domain.Product.CreateProductResponse;
import nl.fontys.s3.comfyshop.domain.Product.UpdateProductRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class ProductsController {
    private final CreateProductUC createProductUC;
    private final UpdateProductUC updateProductUC;
    @PostMapping()
    public ResponseEntity<CreateProductResponse> createProduct(@RequestBody @Valid CreateProductRequest request){
        CreateProductResponse response = createProductUC.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PutMapping("{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable("id") long id, @RequestBody @Valid UpdateProductRequest request){
        request.setId(id);
        updateProductUC.updateProduct(request);
        return ResponseEntity.noContent().build();
    }
}
