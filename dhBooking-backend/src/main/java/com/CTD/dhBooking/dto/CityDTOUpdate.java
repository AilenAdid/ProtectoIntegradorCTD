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
public class CityDTOUpdate {
    @NotNull
    private Integer id;
    @NotBlank
    @Size(min=2, max=100)
    private String name;
}
