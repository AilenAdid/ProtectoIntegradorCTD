package com.CTD.dhBooking.service.interfaces;

import com.CTD.dhBooking.dto.CharacteristicDTOAdd;
import com.CTD.dhBooking.dto.CharacteristicDTOResponse;

import java.util.Set;

public interface ICharacteristicService {
      Set<CharacteristicDTOResponse> listCharacteristics();
      CharacteristicDTOResponse addCharacteristic(CharacteristicDTOAdd category);
}
