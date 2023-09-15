package com.CTD.dhBooking.dto;

import com.CTD.dhBooking.security.dto.UserDTOResponse;
import com.CTD.dhBooking.validation.Onwards;
import lombok.*;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTOAdd {
    @NotNull
    private ProductDTOResponse product;
    @NotNull
    private UserDTOResponse user;
    @Onwards @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;
    @NotNull
    private Time startTime;
}
