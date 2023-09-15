package com.CTD.dhBooking.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.URL;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "category")
public class Category {
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private Integer id;

   @Column
   private String title;

   @Column
   private String description;

   @Column(name = "url_image")
   private String urlImage;

   @OneToMany(mappedBy = "category")
   @JsonIgnore
   private Set<Product> products = new HashSet<>();
}
