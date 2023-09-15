package com.CTD.dhBooking.entities;
import com.CTD.dhBooking.security.entities.User;
import lombok.*;
import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "reservation")
public class Reservation {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   @Column(name = "start_date")
   private LocalDate startDate;

   @Column(name = "end_date")
   private LocalDate endDate;

   @Column(name = "start_time")
   private Time startTime;

   @ManyToOne
   @JoinColumn(name = "product_id")
   private Product product;

   @ManyToOne
   @JoinColumn(name = "user_id")
   private User user;
}
