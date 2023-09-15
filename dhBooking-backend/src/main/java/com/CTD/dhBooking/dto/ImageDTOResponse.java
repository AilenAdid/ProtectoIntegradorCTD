package com.CTD.dhBooking.dto;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageDTOResponse {
    private Integer id;
    private String title;
    private String url;
}
