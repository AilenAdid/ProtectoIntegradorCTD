package com.CTD.dhBooking.service.interfaces;
import com.CTD.dhBooking.dto.ProductDTOAdd;
import com.CTD.dhBooking.dto.ProductDTOResponse;
import com.CTD.dhBooking.dto.ProductDTOUpdate;
import com.CTD.dhBooking.exceptions.BadRequestException;
import com.CTD.dhBooking.exceptions.ResourceNotFoundException;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface IProductService {
    Set<ProductDTOResponse> listProducts();
    ProductDTOResponse searchProductById(Integer Id) throws ResourceNotFoundException;
    Set<ProductDTOResponse> searchProductsByCategory(String title);
    Set<ProductDTOResponse> searchProductsByCityName(String name);
    List<ProductDTOResponse> searchProductsByInputDates(LocalDate endDate, LocalDate startDate, String city);
    List<Date> getAvailableDays(Integer id);
    ProductDTOResponse addProduct(ProductDTOAdd product) throws BadRequestException;
    ProductDTOResponse updateProduct(ProductDTOUpdate product) throws BadRequestException, ResourceNotFoundException;
    void deleteProduct(Integer id) throws ResourceNotFoundException;
}
