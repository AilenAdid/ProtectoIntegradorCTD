package com.CTD.dhBooking.dto;
import lombok.*;
import org.hibernate.validator.constraints.URL;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTOUpdate {
    @NotNull
    private Integer id;
    @NotBlank
    @Size(min=2, max= 70)
    private String title;
    @NotBlank
    @Size(min=3, max=1000)
    private String description;
    @NotBlank
    @URL
    private String urlImage;
}
