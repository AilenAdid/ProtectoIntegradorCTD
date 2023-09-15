package com.CTD.dhBooking.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "policy")
public class Policy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "description_rules")
    private String descriptionRules;

    @Column(name = "description_securityandhealth")
    private String descriptionSecurityAndHealth;

    @Column(name = "description_cancellation")
    private String descriptionCancellation;

    @OneToOne(mappedBy = "policy")
    @JsonIgnore
    private Product product;

}
