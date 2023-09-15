package com.CTD.dhBooking.dto;
import lombok.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PolicyDTOUpdate {
    @NotNull
    private Integer id;
    @NotBlank
    @Size(min=3)
    private String descriptionRules;
    @NotBlank
    @Size(min=3)
    private String descriptionSecurityAndHealth;
    @NotBlank
    @Size(min=3)
    private String descriptionCancellation;

}
