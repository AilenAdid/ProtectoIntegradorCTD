package com.CTD.dhBooking.service.implementation;
import com.CTD.dhBooking.dto.CategoryDTOAdd;
import com.CTD.dhBooking.dto.CategoryDTOResponse;
import com.CTD.dhBooking.dto.CategoryDTOUpdate;
import com.CTD.dhBooking.entities.Category;
import com.CTD.dhBooking.exceptions.ResourceNotFoundException;
import com.CTD.dhBooking.repository.CategoryRepository;
import com.CTD.dhBooking.service.interfaces.ICategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryServiceImpl implements ICategoryService {


   public static final Logger logger = Logger.getLogger(CategoryServiceImpl.class);
   @Autowired
   private CategoryRepository categoryRepository;
   @Autowired
   private ObjectMapper mapper;

   @Override
   public Set<CategoryDTOResponse> listCategories() {
      List<Category> categories = categoryRepository.findAll();
      Set<CategoryDTOResponse> categoriesDTO = new HashSet<>();
      for (Category category : categories) {
         categoriesDTO.add(mapper.convertValue(category, CategoryDTOResponse.class));
      }
      return categoriesDTO;
   }

   @Override
   public CategoryDTOResponse addCategory(CategoryDTOAdd category) {
      Category savedCategory = categoryRepository.save(mapper.convertValue(category, Category.class));
      return mapper.convertValue(savedCategory, CategoryDTOResponse.class);
   }

   @Override
   public CategoryDTOResponse searchCategoryById(Integer id) throws ResourceNotFoundException {
      Optional<Category> categorySearched = categoryRepository.findById(id);
      if (categorySearched.isEmpty()) {
         logger.error("========= ID:"+ id + " not found.");
         throw new ResourceNotFoundException("Category #" + id + " not found.");
      }
      return mapper.convertValue(categorySearched.get(), CategoryDTOResponse.class);
   }

   @Override
   public CategoryDTOResponse updateCategory(CategoryDTOUpdate category) throws ResourceNotFoundException {
      if (!categoryRepository.existsById(category.getId())) {
         logger.error("========= ID:"+ category.getId() + " not found.");
         throw new ResourceNotFoundException("Category #" + category.getId() + " not found. Can not be updated.");
      }
      categoryRepository.save(mapper.convertValue(category, Category.class));
      return mapper.convertValue(category, CategoryDTOResponse.class);
   }

   @Override
   public void deleteCategory(Integer id) throws ResourceNotFoundException {
      if (!categoryRepository.existsById(id)) {
         logger.error("========= ID:"+ id + " not found.");
         throw new ResourceNotFoundException("Category #" + id + " not found. Can not be deleted.");
      }
      categoryRepository.deleteById(id);
   }
}
