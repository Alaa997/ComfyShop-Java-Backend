package nl.fontys.s3.comfyshop.bussiness.product.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import nl.fontys.s3.comfyshop.bussiness.exception.InvalidCategoryException;
import nl.fontys.s3.comfyshop.bussiness.exception.InvalidRequestException;
import nl.fontys.s3.comfyshop.bussiness.exception.NameAlreadyExistsException;
import nl.fontys.s3.comfyshop.bussiness.product.CreateProductUC;
import nl.fontys.s3.comfyshop.dto.CategoryDTO;
import nl.fontys.s3.comfyshop.dto.ProductDTO;
import nl.fontys.s3.comfyshop.mappers.ProductMapper;
import nl.fontys.s3.comfyshop.persistence.CategoryRepository;
import nl.fontys.s3.comfyshop.persistence.CloudinaryRepo;
import nl.fontys.s3.comfyshop.persistence.ProductRepository;
import nl.fontys.s3.comfyshop.persistence.entity.CategoryEntity;
import nl.fontys.s3.comfyshop.persistence.entity.ProductEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CreateProductUCImpl implements CreateProductUC {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final CloudinaryRepo cloudinaryRepo;

    @Override
    public ProductDTO createProduct(MultipartHttpServletRequest request) {
        ProductDTO productDTO = extractProductDTO(request);
        handleFileUpload(productDTO, request);
        validateProductDTO(productDTO);
        CategoryEntity categoryEntity = getCategoryEntity(productDTO);
        ProductEntity savedProduct = saveProduct(productDTO, categoryEntity);
        return ProductMapper.mapperToDTO(savedProduct);
    }

    private ProductDTO extractProductDTO(MultipartHttpServletRequest request) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(request.getParameter("name"));
        productDTO.setDescription(request.getParameter("description"));
        productDTO.setPrice(Double.parseDouble(request.getParameter("price")));
        extractCategoryFromJson(productDTO, request.getParameter("category"));
        return productDTO;
    }

    private void extractCategoryFromJson(ProductDTO productDTO, String categoryJson) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            CategoryDTO categoryDTO = objectMapper.readValue(categoryJson, CategoryDTO.class);
            productDTO.setCategory(categoryDTO);
        } catch (JsonProcessingException e) {
            throw new InvalidRequestException("Invalid category JSON");
        }
    }

    private void handleFileUpload(ProductDTO productDTO, MultipartHttpServletRequest request) {
        MultipartFile photo = request.getFile("photo");
        String url = cloudinaryRepo.uploadPicture(photo);
        productDTO.setPhoto(url);
    }

    private void validateProductDTO(ProductDTO productDTO) {
        if (existsByName(productDTO.getName())) {
            throw new NameAlreadyExistsException();
        }
        Optional<CategoryEntity> categoryOptional = categoryRepository.findById(productDTO.getCategory().getId());
        if (!categoryOptional.isPresent()) {
            throw new InvalidCategoryException("CATEGORY_ID_INVALID");
        }
    }

    private CategoryEntity getCategoryEntity(ProductDTO productDTO) {
        return categoryRepository.findById(productDTO.getCategory().getId())
                .orElseThrow(() -> new InvalidCategoryException("CATEGORY_ID_INVALID"));
    }

    private ProductEntity saveProduct(ProductDTO productDTO, CategoryEntity categoryEntity) {
        ProductEntity productEntity = ProductMapper.mapperToEntity(productDTO);
        productEntity.setCategory(categoryEntity);
        return productRepository.save(productEntity);
    }

    private boolean existsByName(String name) {
        return productRepository.existsByName(name);
    }
}
