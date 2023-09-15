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
public class ImageDTOUpdate {
    @NotNull
    private Integer id;
    @NotBlank
    @Size(min=2, max= 70)
    private String title;
    @NotBlank
    @URL
    private String url;
}
