package nl.fontys.s3.comfyshop.controller;

import lombok.AllArgsConstructor;
import nl.fontys.s3.comfyshop.bussiness.category.CreateCategoryUC;
import nl.fontys.s3.comfyshop.bussiness.category.DeleteCategoryUC;
import nl.fontys.s3.comfyshop.bussiness.category.GetCategoriesUC;
import nl.fontys.s3.comfyshop.bussiness.category.UpdateCategoryUC;
import nl.fontys.s3.comfyshop.dto.CategoryDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/categories")
@AllArgsConstructor
//@IsAuthenticated
public class CategoriesController {
    private final CreateCategoryUC createCategoryUC;
    private final UpdateCategoryUC updateCategoryUC;
    private final GetCategoriesUC getCategoriesUC;
    private final DeleteCategoryUC deleteCategoryUC;

//    @IsAuthenticated
//    @RolesAllowed({"ROLE_CUSTOMER", "ROLE_ADMIN"})
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getCategories() {
        return ResponseEntity.ok(getCategoriesUC.getCategories());
    }

//    @IsAuthenticated
//    @RolesAllowed({"ROLE_ADMIN"})
    @PostMapping()
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody @Valid CategoryDTO request) {
        CategoryDTO response = createCategoryUC.createCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
//    @IsAuthenticated
    @PutMapping({"id"})
    public ResponseEntity<Void> updateCategory(@PathVariable("id") Long id, @RequestBody @Valid CategoryDTO request) {
        request.setId(id);
        updateCategoryUC.updateCategory(request);
        return ResponseEntity.noContent().build();
    }
//    @IsAuthenticated
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        deleteCategoryUC.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

}
