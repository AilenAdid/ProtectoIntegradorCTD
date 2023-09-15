package com.CTD.dhBooking.security.dto;
import com.CTD.dhBooking.dto.CityDTOResponse;
import com.CTD.dhBooking.dto.ReservationDTOResponse;
import lombok.*;

import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTOResponse {
   private Integer id;
   private String name;
   private String lastname;
   private String email;
   private RoleDTO role;
   private String token;
}
