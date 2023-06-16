package nl.fontys.s3.comfyshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.fontys.s3.comfyshop.bussiness.category.CreateCategoryUC;
import nl.fontys.s3.comfyshop.bussiness.category.DeleteCategoryUC;
import nl.fontys.s3.comfyshop.bussiness.category.GetCategoriesUC;
import nl.fontys.s3.comfyshop.bussiness.category.UpdateCategoryUC;
import nl.fontys.s3.comfyshop.bussiness.exception.InvalidCategoryException;
import nl.fontys.s3.comfyshop.dto.CategoryDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class CategoriesControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CreateCategoryUC createCategoryUC;
    @MockBean
    private UpdateCategoryUC updateCategoryUC;
    @MockBean
    private GetCategoriesUC getCategoriesUC;
    @MockBean
    private DeleteCategoryUC deleteCategoryUC;

    @Test
    void testGetCategories() throws Exception {
        CategoryDTO category1 = CategoryDTO.builder()
                .id(1L)
                .name("Pants")
                .build();
        CategoryDTO category2 = CategoryDTO.builder()
                .id(2L)
                .name("Skirt")
                .build();
        List<CategoryDTO> categories = new ArrayList<>();
        categories.add(category1);
        categories.add(category2);

        when(getCategoriesUC.getCategories()).thenReturn(categories);

        mockMvc.perform(MockMvcRequestBuilders.get("/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Pants"))
                .andExpect(jsonPath("$[1].name").value("Skirt"));

        verify(getCategoriesUC).getCategories();
    }

    @Test
    @WithMockUser(username = "ali@gmail.com", roles = {"ADMIN"})
    void createCategory() throws Exception {
        CategoryDTO request = CategoryDTO.builder()
                .name("Pants")
                .build();

        CategoryDTO response = CategoryDTO.builder()
                .id(1L)
                .name("Pants")
                .build();

        when(createCategoryUC.createCategory(request)).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.post("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Pants"));

    }

    @Test
    @WithMockUser(username = "ali@gmail.com", roles = {"ADMIN"})
    void testDeleteCategory() throws Exception {
        mockMvc.perform(delete("/categories/{id}", 1L))
                .andExpect(status().isNoContent());

        verify(deleteCategoryUC, times(1)).deleteCategory(1L);
    }

    @Test
    @WithMockUser(username = "ali@gmail.com", roles = {"ADMIN"})
    void testDeleteCategory_NoCategoryId_BadRequest() throws Exception {
        doThrow(new InvalidCategoryException("CATEGORY_ID_INVALID")).when(deleteCategoryUC).deleteCategory(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/categories/1"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        verify(deleteCategoryUC).deleteCategory(1L);
    }

//    @Test
//    @WithMockUser(username = "ali@gmail.com", roles = {"ADMIN"})
//    void testUpdateCategory() throws Exception {
//        CategoryDTO updatedCategory = CategoryDTO.builder()
//                .id(1L)
//                .name("Updated Category")
//                .build();
//
//        doNothing().when(updateCategoryUC).updateCategory(any(CategoryDTO.class));
//
//        mockMvc.perform(put("/categories/{id}", 1L)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(new ObjectMapper().writeValueAsString(updatedCategory)))
//                .andExpect(status().isNoContent());
//
//        verify(updateCategoryUC, times(1)).updateCategory(eq(updatedCategory));
//    }
}