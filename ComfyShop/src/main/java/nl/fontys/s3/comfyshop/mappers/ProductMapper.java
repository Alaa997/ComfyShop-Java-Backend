package nl.fontys.s3.comfyshop.mappers;

import nl.fontys.s3.comfyshop.dto.ProductDTO;
import nl.fontys.s3.comfyshop.persistence.entity.ProductEntity;
import org.modelmapper.ModelMapper;

import java.util.List;

public class ProductMapper {
    public ProductMapper() {
    }

    public static ProductDTO mapperToDTO(ProductEntity productEntity) {
        ModelMapper modelMapper = new ModelMapper();
        ProductDTO productDTO = modelMapper.map(productEntity, ProductDTO.class);
        return productDTO;
    }

    public static ProductEntity mapperToEntity(ProductDTO productDTO) {
        ModelMapper modelMapper = new ModelMapper();
        ProductEntity productEntity = modelMapper.map(productDTO, ProductEntity.class);
        return productEntity;
    }
    public static List<ProductDTO> mapperToDTOList(List<ProductEntity> productEntityList){
        return productEntityList.stream().map(ProductMapper::mapperToDTO).toList();
    }
    public static List<ProductEntity> mapperToEntityList(List<ProductDTO> productEntityList){
        return productEntityList.stream().map(ProductMapper::mapperToEntity).toList();
    }
}
