package com.CTD.dhBooking.service.interfaces;
import com.CTD.dhBooking.dto.CategoryDTOAdd;
import com.CTD.dhBooking.dto.CategoryDTOResponse;
import com.CTD.dhBooking.dto.CategoryDTOUpdate;
import com.CTD.dhBooking.exceptions.BadRequestException;
import com.CTD.dhBooking.exceptions.ResourceNotFoundException;
import java.util.Set;

public interface ICategoryService {
   Set<CategoryDTOResponse> listCategories();
   CategoryDTOResponse addCategory(CategoryDTOAdd category);
   CategoryDTOResponse searchCategoryById(Integer id) throws ResourceNotFoundException;
   CategoryDTOResponse updateCategory(CategoryDTOUpdate category) throws ResourceNotFoundException, BadRequestException;
   void deleteCategory(Integer id) throws ResourceNotFoundException;
}
