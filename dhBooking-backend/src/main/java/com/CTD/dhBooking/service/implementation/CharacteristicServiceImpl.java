package com.CTD.dhBooking.service.implementation;

import com.CTD.dhBooking.dto.CharacteristicDTOAdd;
import com.CTD.dhBooking.dto.CharacteristicDTOResponse;
import com.CTD.dhBooking.entities.Characteristic;
import com.CTD.dhBooking.repository.CharacteristicRepository;
import com.CTD.dhBooking.service.interfaces.ICharacteristicService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Service
public class CharacteristicServiceImpl implements ICharacteristicService {
   public static final Logger logger = Logger.getLogger(CharacteristicServiceImpl.class);
   @Autowired
   private CharacteristicRepository characteristicRepository;
   @Autowired
   private ObjectMapper mapper;

   @Override
   public Set<CharacteristicDTOResponse> listCharacteristics() {
      List<Characteristic> characteristics = characteristicRepository.findAll();
      Set<CharacteristicDTOResponse> characteristicDTO = new HashSet<>();
      for (Characteristic characteristic : characteristics) {
         characteristicDTO.add(mapper.convertValue(characteristic, CharacteristicDTOResponse.class));
      }
      return characteristicDTO;
   }

   @Override
   public CharacteristicDTOResponse addCharacteristic(CharacteristicDTOAdd characteristic) {
      Characteristic savedCharacteristic = characteristicRepository.save(mapper.convertValue(characteristic, Characteristic.class));
      return mapper.convertValue(savedCharacteristic, CharacteristicDTOResponse.class);
   }
}
