package com.CTD.dhBooking.controller;
import com.CTD.dhBooking.dto.CategoryDTOAdd;
import com.CTD.dhBooking.dto.CategoryDTOResponse;
import com.CTD.dhBooking.dto.CategoryDTOUpdate;
import com.CTD.dhBooking.exceptions.BadRequestException;
import com.CTD.dhBooking.exceptions.ResourceNotFoundException;
import com.CTD.dhBooking.service.implementation.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping ("/categories")
@CrossOrigin(origins = "*")
public class CategoryController {
   @Autowired
   private CategoryServiceImpl categoryService;

   @GetMapping
   public ResponseEntity<Set<CategoryDTOResponse>> listCategories(){
      return ResponseEntity.ok(categoryService.listCategories());
   }

   @PostMapping
   public ResponseEntity<CategoryDTOResponse> addCategory (@Valid @RequestBody CategoryDTOAdd category) throws BadRequestException{
      return ResponseEntity.ok(categoryService.addCategory(category));
   }

   @GetMapping("/{id}")
   public ResponseEntity<CategoryDTOResponse> searchCategory(@PathVariable Integer id) throws ResourceNotFoundException{
      return ResponseEntity.ok(categoryService.searchCategoryById(id));
   }

   @PutMapping
   public ResponseEntity<CategoryDTOResponse> updateCategory (@Valid @RequestBody CategoryDTOUpdate category) throws ResourceNotFoundException, BadRequestException {
      return ResponseEntity.ok(categoryService.updateCategory(category));
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<?> deleteCategory(@PathVariable Integer id) throws ResourceNotFoundException {
      categoryService.deleteCategory(id);
      return ResponseEntity.ok().build();
   }
}
