package com.CTD.dhBooking.security.entities;
import com.CTD.dhBooking.entities.City;
import com.CTD.dhBooking.entities.Reservation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "user")
public class User implements UserDetails {
   @Id
   @Column
   @GeneratedValue(strategy= GenerationType.IDENTITY)
   private Integer id;

   @Column
   private String name;

   @Column
   private String lastname;

   @Column
   private String email;

   @Column
   private String password;

   @ManyToOne(cascade = CascadeType.MERGE)
   @JoinColumn(name = "role_id")
   private Role role;

   @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
   @JsonIgnore
   private Set<Reservation> reservations;

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return Collections.emptyList();
   }

   @Override
   public String getUsername() {
      return name+" "+lastname;
   }

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return true;
   }
}


