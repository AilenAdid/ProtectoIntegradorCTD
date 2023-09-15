package com.CTD.dhBooking.service.implementation;
import com.CTD.dhBooking.dto.ImageDTOAdd;
import com.CTD.dhBooking.dto.ImageDTOResponse;
import com.CTD.dhBooking.dto.ImageDTOUpdate;
import com.CTD.dhBooking.entities.Image;
import com.CTD.dhBooking.exceptions.BadRequestException;
import com.CTD.dhBooking.exceptions.ResourceNotFoundException;
import com.CTD.dhBooking.repository.ImageRepository;
import com.CTD.dhBooking.service.interfaces.IImageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ImageServiceImpl implements IImageService {
    public static final Logger logger = Logger.getLogger(ImageServiceImpl.class);
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ObjectMapper mapper;

    @Override
    public Set<ImageDTOResponse> listImages() {
        List<Image> images = imageRepository.findAll();
        Set<ImageDTOResponse> imagesDTO = new HashSet<>();
        for (Image image : images) {
            imagesDTO.add(mapper.convertValue(image, ImageDTOResponse.class));
        }
        return imagesDTO;
    }

    @Override
    public ImageDTOResponse addImage(ImageDTOAdd image) {
        Image savedImage = imageRepository.save(mapper.convertValue(image, Image.class));
        return mapper.convertValue(savedImage, ImageDTOResponse.class);
    }

    @Override
    public ImageDTOResponse searchImageById(Integer id) throws ResourceNotFoundException {
        Optional<Image> imageSearched = imageRepository.findById(id);
        if (imageSearched.isEmpty()) {
            logger.error ("========= ID:"+ id + " not found.");
            throw new ResourceNotFoundException("Image #" + id + " not found.");
        }
        return mapper.convertValue(imageSearched.get(), ImageDTOResponse.class);
    }

    @Override
    public ImageDTOResponse updateImage(ImageDTOUpdate image) throws ResourceNotFoundException, BadRequestException {
        if (!imageRepository.existsById(image.getId())) {
            logger.error ("========= ID:"+ image.getId() + " not found.");
            throw new ResourceNotFoundException("Image #" + image.getId() + " not found. Can not be updated.");
        }
        imageRepository.save(mapper.convertValue(image, Image.class));
        return mapper.convertValue(image, ImageDTOResponse.class);
    }

    @Override
    public void deleteImage(Integer id) throws ResourceNotFoundException {
        if (!imageRepository.existsById(id)) {
            logger.error ("========= ID:"+ id + " not found.");
            throw new ResourceNotFoundException("Image #" + id + " not found. Can not be deleted.");
        }
        imageRepository.deleteById(id);
    }
}
