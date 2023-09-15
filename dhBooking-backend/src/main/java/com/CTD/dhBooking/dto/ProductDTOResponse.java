package com.CTD.dhBooking.dto;
import lombok.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTOResponse {
    private Integer id;
    private String title;
    private String description;
    private CategoryDTOResponse category;
    private AddressDTOResponse address;
    private Set<ImageDTOResponse> images;
    private Set<CharacteristicDTOResponse> characteristics;
    private PolicyDTOResponse policy;
    private List<Date> availableDays;
}

