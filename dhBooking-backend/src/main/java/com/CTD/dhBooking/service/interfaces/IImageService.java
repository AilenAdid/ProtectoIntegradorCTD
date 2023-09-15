package com.CTD.dhBooking.service.interfaces;
import com.CTD.dhBooking.dto.ImageDTOAdd;
import com.CTD.dhBooking.dto.ImageDTOResponse;
import com.CTD.dhBooking.dto.ImageDTOUpdate;
import com.CTD.dhBooking.exceptions.BadRequestException;
import com.CTD.dhBooking.exceptions.ResourceNotFoundException;
import java.util.Set;

public interface IImageService {
    Set<ImageDTOResponse> listImages();
    ImageDTOResponse addImage(ImageDTOAdd image) throws BadRequestException;
    ImageDTOResponse searchImageById(Integer id) throws ResourceNotFoundException;
    ImageDTOResponse updateImage(ImageDTOUpdate image) throws ResourceNotFoundException, BadRequestException;
    void deleteImage(Integer id) throws ResourceNotFoundException;
}
