package com.CTD.dhBooking.service.interfaces;
import com.CTD.dhBooking.dto.CityDTOAdd;
import com.CTD.dhBooking.dto.CityDTOResponse;
import com.CTD.dhBooking.dto.CityDTOUpdate;
import com.CTD.dhBooking.exceptions.BadRequestException;
import com.CTD.dhBooking.exceptions.ResourceNotFoundException;
import java.util.Set;

public interface ICityService {
    Set<CityDTOResponse> listCities();
    CityDTOResponse addCity(CityDTOAdd city);
    CityDTOResponse searchCityById(Integer id) throws ResourceNotFoundException;
    CityDTOResponse updateCity(CityDTOUpdate city) throws ResourceNotFoundException;
    void deleteCity(Integer id) throws ResourceNotFoundException;
}
