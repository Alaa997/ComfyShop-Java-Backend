package nl.fontys.s3.comfyshop.bussiness.product.impl;

import nl.fontys.s3.comfyshop.bussiness.exception.InvalidCategoryException;
import nl.fontys.s3.comfyshop.bussiness.exception.NameAlreadyExistsException;
import nl.fontys.s3.comfyshop.dto.CategoryDTO;
import nl.fontys.s3.comfyshop.dto.ProductDTO;
import nl.fontys.s3.comfyshop.mappers.CategoryMapper;
import nl.fontys.s3.comfyshop.mappers.ProductMapper;
import nl.fontys.s3.comfyshop.persistence.CategoryRepository;
import nl.fontys.s3.comfyshop.persistence.CloudinaryRepo;
import nl.fontys.s3.comfyshop.persistence.ProductRepository;
import nl.fontys.s3.comfyshop.persistence.entity.CategoryEntity;
import nl.fontys.s3.comfyshop.persistence.entity.ProductEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateProductUCImplTest {
    @Mock
    private ProductRepository productRepositoryMock;
    @Mock
    private CategoryRepository categoryRepositoryMock;
    @Mock
    private CloudinaryRepo cloudinaryRepoMock;
    @InjectMocks
    private CreateProductUCImpl createProductUC;

    @Test
    void createProduct_Success() {
        // Arrange
        CategoryDTO categoryDTO = CategoryDTO.builder().id(1L).name("Meat").build();
        ProductDTO productDTO = ProductDTO.builder()
                .name("Ribs")
                .description("Tasty")
                .price(5.5)
                .category(categoryDTO)
                .build();

        MultipartHttpServletRequest request = Mockito.mock(MultipartHttpServletRequest.class);
        when(request.getParameter("name")).thenReturn(productDTO.getName());
        when(request.getParameter("description")).thenReturn(productDTO.getDescription());
        when(request.getParameter("price")).thenReturn(String.valueOf(productDTO.getPrice()));
        when(request.getParameter("category")).thenReturn("{\"id\": 1, \"name\": \"Meat\"}");

        MultipartFile mockFile = Mockito.mock(MultipartFile.class);
        when(request.getFile("photo")).thenReturn(mockFile);

        String imageUrl = "https://example.com/image.jpg";
        when(cloudinaryRepoMock.uploadPicture(mockFile)).thenReturn(imageUrl);

        when(productRepositoryMock.existsByName(productDTO.getName())).thenReturn(false);

        CategoryEntity categoryEntity = CategoryMapper.mapperToEntity(categoryDTO);
        when(categoryRepositoryMock.findById(productDTO.getCategory().getId())).thenReturn(Optional.of(categoryEntity));

        ProductEntity savedProduct = ProductMapper.mapperToEntity(productDTO);
        when(productRepositoryMock.save(any(ProductEntity.class))).thenReturn(savedProduct);

        // Act
        ProductDTO result = createProductUC.createProduct(request);

        // Assert
        assertNotNull(result);
        assertEquals(productDTO.getName(), result.getName());
        assertEquals(productDTO.getCategory().getId(), result.getCategory().getId());
    }

    @Test
    void createProduct_WithExistingName_Exception() {
        // Arrange
        CategoryDTO categoryDTO = CategoryDTO.builder().id(1L).name("Meat").build();
        ProductDTO productDTO = ProductDTO.builder()
                .name("Ribs")
                .description("Tasty")
                .price(5.5)
                .category(categoryDTO)
                .build();

        MultipartHttpServletRequest request = Mockito.mock(MultipartHttpServletRequest.class);
        when(request.getParameter("name")).thenReturn(productDTO.getName());
        when(request.getParameter("description")).thenReturn(productDTO.getDescription());
        when(request.getParameter("price")).thenReturn(String.valueOf(productDTO.getPrice()));
        when(request.getParameter("category")).thenReturn("{\"id\": 1, \"name\": \"Meat\"}");

        MultipartFile mockFile = Mockito.mock(MultipartFile.class);
        when(request.getFile("photo")).thenReturn(mockFile);

        when(productRepositoryMock.existsByName(productDTO.getName())).thenReturn(true);

        // Act and Assert
        assertThrows(NameAlreadyExistsException.class, () -> createProductUC.createProduct(request));
        verify(categoryRepositoryMock, never()).findById(productDTO.getCategory().getId());
        verify(productRepositoryMock, never()).save(any(ProductEntity.class));
    }

    @Test
    void createProduct_WithInvalidCategoryId_Exception() {
        // Arrange
        CategoryDTO categoryDTO = CategoryDTO.builder().id(1L).name("Meat").build();
        ProductDTO productDTO = ProductDTO.builder()
                .name("Ribs")
                .description("Tasty")
                .price(5.5)
                .category(categoryDTO)
                .build();

        MultipartHttpServletRequest request = Mockito.mock(MultipartHttpServletRequest.class);
        when(request.getParameter("name")).thenReturn(productDTO.getName());
        when(request.getParameter("description")).thenReturn(productDTO.getDescription());
        when(request.getParameter("price")).thenReturn(String.valueOf(productDTO.getPrice()));
        when(request.getParameter("category")).thenReturn("{\"id\": 1, \"name\": \"Meat\"}");

        MultipartFile mockFile = Mockito.mock(MultipartFile.class);
        when(request.getFile("photo")).thenReturn(mockFile);

        when(productRepositoryMock.existsByName(productDTO.getName())).thenReturn(false);
        when(categoryRepositoryMock.findById(productDTO.getCategory().getId())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(InvalidCategoryException.class, () -> createProductUC.createProduct(request));
        verify(productRepositoryMock, times(1)).existsByName(productDTO.getName());
        verify(productRepositoryMock, never()).save(any(ProductEntity.class));
    }
}