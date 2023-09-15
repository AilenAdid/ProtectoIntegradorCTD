package com.CTD.dhBooking.dto;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTOResponse {
    private Integer id;
    private String title;
    private String description;
    private String urlImage;
}
