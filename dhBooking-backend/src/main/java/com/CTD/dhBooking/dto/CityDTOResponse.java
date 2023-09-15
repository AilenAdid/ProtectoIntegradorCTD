package com.CTD.dhBooking.dto;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CityDTOResponse {
    private Integer id;
    private String name;
}
