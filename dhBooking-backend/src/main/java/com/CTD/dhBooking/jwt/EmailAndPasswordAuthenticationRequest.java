package com.CTD.dhBooking.jwt;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class EmailAndPasswordAuthenticationRequest {
    private String email;
    private String password;
}
