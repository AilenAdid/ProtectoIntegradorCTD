package com.CTD.dhBooking.dto;
import lombok.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CharacteristicDTOAdd {
    @NotBlank
    @Size(min=3, max=255)
    private String title;
}
