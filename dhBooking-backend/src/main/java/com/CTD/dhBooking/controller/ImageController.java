package com.CTD.dhBooking.controller;
import com.CTD.dhBooking.dto.ImageDTOAdd;
import com.CTD.dhBooking.dto.ImageDTOResponse;
import com.CTD.dhBooking.dto.ImageDTOUpdate;
import com.CTD.dhBooking.exceptions.BadRequestException;
import com.CTD.dhBooking.exceptions.ResourceNotFoundException;
import com.CTD.dhBooking.service.implementation.ImageServiceImpl;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/images")
@CrossOrigin(origins = "*")
public class ImageController {
   @Autowired
    private ImageServiceImpl imageService;

   @GetMapping
   public ResponseEntity<Set<ImageDTOResponse>> listImages() {
      return ResponseEntity.ok(imageService.listImages());
   }

   @PostMapping
   public ResponseEntity<ImageDTOResponse> addImage (@Valid @RequestBody ImageDTOAdd image) {
      return ResponseEntity.ok(imageService.addImage(image));
   }

   @GetMapping("/{id}")
   public ResponseEntity<ImageDTOResponse> searchImage(@PathVariable Integer id) throws ResourceNotFoundException{
      return ResponseEntity.ok(imageService.searchImageById(id));
   }

   @PutMapping
   public ResponseEntity<ImageDTOResponse> updateImage(@Valid @RequestBody ImageDTOUpdate image) throws BadRequestException, ResourceNotFoundException {
      return ResponseEntity.ok(imageService.updateImage(image));
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<?> deleteImage(@PathVariable Integer id) throws ResourceNotFoundException {
      imageService.deleteImage(id);
      return ResponseEntity.ok().build();
   }
}
