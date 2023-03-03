package nl.fontys.s3.comfyshop.controller;

import lombok.AllArgsConstructor;
import nl.fontys.s3.comfyshop.bussiness.Category.CreateCategoryUC;
import nl.fontys.s3.comfyshop.domain.Category.CreateCategoryRequest;
import nl.fontys.s3.comfyshop.domain.Category.CreateCategoryResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class CategoriesController {
    private final CreateCategoryUC createCategoryUC;

    @PostMapping()
    public ResponseEntity<CreateCategoryResponse> createCategory(@RequestBody @Valid CreateCategoryRequest request){
        CreateCategoryResponse response = createCategoryUC.createCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
