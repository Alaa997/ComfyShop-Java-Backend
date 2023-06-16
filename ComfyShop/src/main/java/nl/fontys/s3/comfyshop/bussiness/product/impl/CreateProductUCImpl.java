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
        ProductDTO productDTO = new ProductDTO();

        // Extract form data
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String price = request.getParameter("price");
        String categoryJson = request.getParameter("category");
        MultipartFile photo = request.getFile("photo");

        // Set the extracted data to the productDTO object
        productDTO.setName(name);
        productDTO.setDescription(description);
        productDTO.setPrice(Double.parseDouble(price));

        // Extract category information from JSON
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            CategoryDTO categoryDTO = objectMapper.readValue(categoryJson, CategoryDTO.class);
            productDTO.setCategory(categoryDTO);
        } catch (JsonProcessingException e) {
            throw new InvalidRequestException("Invalid category JSON");
        }

        // Handle file upload
        String url = cloudinaryRepo.uploadPicture(photo);
        productDTO.setPhoto(url);


        if (existsByName(productDTO.getName())) {
            throw new NameAlreadyExistsException();
        }

        Optional<CategoryEntity> categoryOptional = categoryRepository.findById(productDTO.getCategory().getId());
        if (!categoryOptional.isPresent()) {
            throw new InvalidCategoryException("CATEGORY_ID_INVALID");
        }


        ProductEntity savedProduct = save(ProductMapper.mapperToEntity(productDTO));
        return ProductMapper.mapperToDTO(savedProduct);
    }

    private ProductEntity save(ProductEntity product) {
        return productRepository.save(product);
    }

    private boolean existsByName(String name) {
        return productRepository.existsByName(name);
    }

}
