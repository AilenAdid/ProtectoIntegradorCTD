package com.CTD.dhBooking.service.implementation;
import com.CTD.dhBooking.dto.ProductDTOAdd;
import com.CTD.dhBooking.dto.ProductDTOResponse;
import com.CTD.dhBooking.dto.ProductDTOUpdate;
import com.CTD.dhBooking.entities.*;
import com.CTD.dhBooking.exceptions.ResourceNotFoundException;
import com.CTD.dhBooking.repository.ProductRepository;
import com.CTD.dhBooking.service.interfaces.IProductService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProductServiceImpl implements IProductService {
    public static final Logger logger = Logger.getLogger(ProductServiceImpl.class);
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ObjectMapper mapper;

    @Override
    public Set<ProductDTOResponse> listProducts() {
        List<Product> products = productRepository.findAll();
        Set<ProductDTOResponse> productsDTO = new HashSet<>();
        for (Product p : products) {
            productsDTO.add(mapper.convertValue(p, ProductDTOResponse.class));
        }
        return productsDTO;
    }

    @Override
    public ProductDTOResponse searchProductById(Integer id) throws ResourceNotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            logger.error ("========= ID:"+ id + " not found.");
            throw new ResourceNotFoundException("Product #" + id + "was not found.");
        }
        List<Date> availableDays = productRepository.getAvailableDays(id);
        ProductDTOResponse productDTO = mapper.convertValue(product, ProductDTOResponse.class);
        productDTO.setAvailableDays(availableDays);
        return productDTO;
    }

    @Override
    public Set<ProductDTOResponse> searchProductsByCityName(String name) {
        List<Product> products = productRepository.findAllByCityName(name.substring(0, 1).toUpperCase() + name.substring(1));
        Set<ProductDTOResponse> productsDTO = new HashSet<>();
        for (Product p : products) {
            productsDTO.add(mapper.convertValue(p, ProductDTOResponse.class));
        }
        return productsDTO;
    }

    @Override
    public List<ProductDTOResponse> searchProductsByInputDates(LocalDate endDate, LocalDate startDate, String city) {
        List<Product> products = productRepository.findBySearchedDates(endDate, startDate, city);
        List<ProductDTOResponse> productsDTO = new ArrayList<>();
        for (Product p : products) {
            productsDTO.add(mapper.convertValue(p, ProductDTOResponse.class));
        }
        return productsDTO;
    }

    @Override
    public List<Date> getAvailableDays(Integer id) {
        return productRepository.getAvailableDays(id);
    }

    @Override
    public Set<ProductDTOResponse> searchProductsByCategory(String title) {
        List<Product> products = productRepository.findAllByCategory(title.substring(0, 1).toUpperCase() + title.substring(1));
        Set<ProductDTOResponse> productsDTO = new HashSet<>();
        for (Product p : products) {
            productsDTO.add(mapper.convertValue(p, ProductDTOResponse.class));
        }
        return productsDTO;
    }

    @Override
    public ProductDTOResponse addProduct(ProductDTOAdd product) {
        Product savedProduct = productRepository.save(mapper.convertValue(product, Product.class));
        return mapper.convertValue(savedProduct, ProductDTOResponse.class);
    }

    @Override
    public ProductDTOResponse updateProduct(ProductDTOUpdate product) throws ResourceNotFoundException {
        if (!productRepository.existsById(product.getId())) {
            logger.error ("========= ID:"+ product.getId() + " not found.");
            throw new ResourceNotFoundException("Product #" + product.getId() + " not found. Can not be updated.");
        }
        Product savedProduct = productRepository.save(mapper.convertValue(product, Product.class));
        return mapper.convertValue(savedProduct, ProductDTOResponse.class);
    }

    @Override
    public void deleteProduct(Integer id) throws ResourceNotFoundException{
        if (!productRepository.existsById(id)) {
            logger.error ("========= ID:"+ id + " not found.");
            throw new ResourceNotFoundException("Product #" + id + " not found. Can not be deleted.");
        }
        productRepository.deleteById(id);
    }
}

