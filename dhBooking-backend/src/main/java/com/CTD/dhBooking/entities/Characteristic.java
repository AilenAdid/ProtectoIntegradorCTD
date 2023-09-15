package com.CTD.dhBooking.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "characteristic")
public class Characteristic {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String title;

    @ManyToMany(mappedBy = "characteristics")
    @JsonIgnore
    private Set<Product> products;
}
