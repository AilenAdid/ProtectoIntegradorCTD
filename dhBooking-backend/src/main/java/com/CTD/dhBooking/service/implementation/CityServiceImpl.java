package com.CTD.dhBooking.service.implementation;
import com.CTD.dhBooking.dto.CityDTOAdd;
import com.CTD.dhBooking.dto.CityDTOResponse;
import com.CTD.dhBooking.dto.CityDTOUpdate;
import com.CTD.dhBooking.entities.City;
import com.CTD.dhBooking.exceptions.ResourceNotFoundException;
import com.CTD.dhBooking.repository.CityRepository;
import com.CTD.dhBooking.service.interfaces.ICityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CityServiceImpl implements ICityService {
    public static final Logger logger = Logger.getLogger(CategoryServiceImpl.class);
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    ObjectMapper mapper;

    @Override
    public Set<CityDTOResponse> listCities() {
        List<City> cities = cityRepository.findAll();
        Set<CityDTOResponse> citiesDTO = new HashSet<>();
        for (City city : cities) {
            citiesDTO.add(mapper.convertValue(city, CityDTOResponse.class));
        }
        return citiesDTO;
    }

    @Override
    public CityDTOResponse addCity(CityDTOAdd city) {
        City savedCity = cityRepository.save(mapper.convertValue(city, City.class));
        return mapper.convertValue(city, CityDTOResponse.class);
    }

    @Override
    public CityDTOResponse searchCityById(Integer id) throws ResourceNotFoundException {
        Optional<City> citySearched = cityRepository.findById(id);
        if (citySearched.isEmpty()) {
            logger.error("========= ID:"+ id + " not found.");
            throw new ResourceNotFoundException("City #" + id + " not found.");
        }
        return mapper.convertValue(citySearched, CityDTOResponse.class);
    }

    @Override
    public CityDTOResponse updateCity(CityDTOUpdate city) throws ResourceNotFoundException {
        if (!cityRepository.existsById(city.getId())) {
            logger.error("========= ID:"+ city.getId() + " not found.");
            throw new ResourceNotFoundException("City #" + city.getId() + " not found. Can not be updated.");
        }
        cityRepository.save(mapper.convertValue(city, City.class));
        return mapper.convertValue(city, CityDTOResponse.class);
    }

    @Override
    public void deleteCity(Integer id) throws ResourceNotFoundException {
        if (!cityRepository.existsById(id)) {
            logger.error("========= ID:"+ id + " not found.");
            throw new ResourceNotFoundException("City #" + id + " not found. Can not be deleted.");
        }
        cityRepository.deleteById(id);
    }
}
