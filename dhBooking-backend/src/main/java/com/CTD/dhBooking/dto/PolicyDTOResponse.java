package com.CTD.dhBooking.dto;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PolicyDTOResponse {
    private String descriptionRules;
    private String descriptionSecurityAndHealth;
    private String descriptionCancellation;
}
