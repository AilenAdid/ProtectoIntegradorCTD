package com.CTD.dhBooking.dto;
import lombok.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CityDTOAdd {
    @NotBlank
    @Size(min=2, max=100)
    private String name;
}
