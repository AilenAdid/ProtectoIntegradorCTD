package com.CTD.dhBooking.dto;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageDTOAdd {
    @NotBlank
    @Size(min=2, max= 255)
    private String title;
    @NotBlank
    @URL
    private String url;
}
