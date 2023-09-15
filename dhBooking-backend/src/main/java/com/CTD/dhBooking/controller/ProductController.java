package com.CTD.dhBooking.controller;

import com.CTD.dhBooking.dto.ProductDTOResponse;
import com.CTD.dhBooking.dto.ProductDTOAdd;
import com.CTD.dhBooking.dto.ProductDTOUpdate;
import com.CTD.dhBooking.exceptions.ResourceNotFoundException;
import com.CTD.dhBooking.service.implementation.ProductServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDate;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")
public class ProductController {
    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private ObjectMapper mapper;

    @GetMapping("/searchCity")
    public ResponseEntity<Set<ProductDTOResponse>> searchProductByCityName(@RequestParam String city) {
        return ResponseEntity.ok(productService.searchProductsByCityName(city));
    }

    @GetMapping("/searchCategory")
    public ResponseEntity<Set<ProductDTOResponse>> searchProductByCategory(@RequestParam String category) throws ResourceNotFoundException {
        return ResponseEntity.ok(productService.searchProductsByCategory(category));
    }

    @GetMapping("/searchDates")
    public ResponseEntity<List<ProductDTOResponse>> searchProductSearchedDates(@RequestParam String city, @RequestParam String startDate, @RequestParam String endDate) {
        return ResponseEntity.ok(productService.searchProductsByInputDates(LocalDate.parse(endDate), LocalDate.parse(startDate), city));
    }

    @GetMapping
    public ResponseEntity<Set<ProductDTOResponse>> listProducts() {
        return ResponseEntity.ok(productService.listProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTOResponse> searchProduct(@PathVariable Integer id) throws ResourceNotFoundException {
        return ResponseEntity.ok(productService.searchProductById(id));
    }

    @PostMapping
    public ResponseEntity<ProductDTOResponse> addProduct(@Valid @RequestBody ProductDTOAdd product) {
        return ResponseEntity.ok(productService.addProduct(product));
    }

    @PutMapping
    public ResponseEntity<ProductDTOResponse> updateProduct(@Valid @RequestBody ProductDTOUpdate product) throws ResourceNotFoundException {
        return ResponseEntity.ok(productService.updateProduct(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id) throws ResourceNotFoundException {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
