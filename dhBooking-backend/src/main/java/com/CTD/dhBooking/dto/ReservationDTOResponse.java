package com.CTD.dhBooking.dto;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTOResponse {
    private Integer id;
    private ProductDTOResponse product;
    private ClientDTOResponse user;
    private LocalDate startDate;
    private LocalDate endDate;
    private Time startTime;
}
