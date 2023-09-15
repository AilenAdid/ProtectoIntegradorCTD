package com.CTD.dhBooking.dto;
import lombok.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTOUpdate {
    @NotNull
    private Integer id;
    @NotBlank
    @Size(min=2, max= 70)
    private String title;
    @NotBlank
    @Size(min=3, max=1000)
    private String description;
    @NotNull
    private CategoryDTOResponse category;
    @NotNull
    private AddressDTOResponse address;
    @NotNull
    private Set<ImageDTOResponse> images;
    @NotNull
    private Set<CharacteristicDTOResponse> characteristics;
    @NotNull
    private PolicyDTOResponse policy;

}

