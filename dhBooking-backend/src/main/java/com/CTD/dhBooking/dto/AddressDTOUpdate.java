package com.CTD.dhBooking.dto;
import lombok.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTOUpdate {
    @NotNull
    private Integer id;
    @NotBlank
    @Size(min= 3, max=70)
    private String street;
    @NotBlank
    @Size(max=7)
    private String number;
    @NotBlank
    @Size(min=3, max=7)
    private String zipPostcode;
    @NotNull
    private CityDTOResponse city;
}
