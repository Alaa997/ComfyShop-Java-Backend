package nl.fontys.s3.comfyshop.mappers;

import nl.fontys.s3.comfyshop.dto.CategoryDTO;
import nl.fontys.s3.comfyshop.persistence.entity.CategoryEntity;
import org.modelmapper.ModelMapper;

import java.util.List;

public class CategoryMapper {
    public CategoryMapper() {
    }
    public static CategoryDTO mapperToDTO(CategoryEntity categoryEntity) {
        ModelMapper modelMapper = new ModelMapper();
        CategoryDTO categoryDTO = modelMapper.map(categoryEntity, CategoryDTO.class);
        return categoryDTO;
    }
    public static CategoryEntity  mapperToEntity(CategoryDTO categoryDTO) {
        ModelMapper modelMapper = new ModelMapper();
        CategoryEntity categoryEntity = modelMapper.map(categoryDTO, CategoryEntity.class);
        return categoryEntity;
    }
    public static List<CategoryDTO> mapperToDTOList(List<CategoryEntity> categoryEntityList){
        return categoryEntityList.stream().map(CategoryMapper::mapperToDTO).toList();
    }
    public static List<CategoryEntity> mapperToEntityList(List<CategoryDTO> categoryDTOList){
        return categoryDTOList.stream().map(CategoryMapper::mapperToEntity).toList();
    }
}
