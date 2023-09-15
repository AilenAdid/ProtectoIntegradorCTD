package com.CTD.dhBooking.dto;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTOResponse {
    private Integer id;
    private String name;
    private String lastname;
    private String email;
}
