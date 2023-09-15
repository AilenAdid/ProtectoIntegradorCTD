package com.CTD.dhBooking.security.dto;

import com.CTD.dhBooking.dto.CityDTOResponse;
import com.CTD.dhBooking.security.validation.PasswordConstraint;
import com.CTD.dhBooking.validation.ConvertCase;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTOUpdate {
   @NotBlank
   private Integer id;
   @NotBlank
   @Size(min=2, max=70)
   @ConvertCase(ConvertCase.CaseType.UPPERCASE)
   private String name;
   @NotBlank
   @Size(min=2, max=70)
   @ConvertCase(ConvertCase.CaseType.UPPERCASE)
   private String lastname;
   @NotBlank
   @Email
   @Size(max=70)
   @ConvertCase(ConvertCase.CaseType.UPPERCASE)
   private String email;
   @NotBlank
   @PasswordConstraint(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
   private String password;
   @NotBlank
   private RoleDTO role;

}
