package com.CTD.dhBooking.dto;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTOResponse {
    private Integer id;
    private String street;
    private String number;
    private String zipPostcode;
    private CityDTOResponse city;
}
