package com.CTD.dhBooking.repository;
import com.CTD.dhBooking.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("FROM Product AS p WHERE p.address.city.name = ?1")
    List<Product> findAllByCityName(String name);

    @Query("FROM Product AS p WHERE p.category.title = ?1")
    List<Product> findAllByCategory(String title);

    @Query("SELECT r.startDate, r.endDate FROM Reservation r WHERE r.product.id = ?1")
    List<Object[]> findReservedDatesByProductId(Integer id);

    @Query(value = "select a.Date\n" +
            "\t\tfrom ( \n" +
            "            select curdate() + INTERVAL (a.a + (10 * b.a) + (100 * c.a) + (1000 * d.a) ) DAY as Date\n" +
            "            from (select 0 as a union all select 1 union all select 2 union all select 3 union all select 4 union all select 5 union all select 6 union all select 7 union all select 8 union all select 9) as a\n" +
            "            cross join (select 0 as a union all select 1 union all select 2 union all select 3 union all select 4 union all select 5 union all select 6 union all select 7 union all select 8 union all select 9) as b\n" +
            "            cross join (select 0 as a union all select 1 union all select 2 union all select 3 union all select 4 union all select 5 union all select 6 union all select 7 union all select 8 union all select 9) as c\n" +
            "            cross join (select 0 as a union all select 1 union all select 2 union all select 3 union all select 4 union all select 5 union all select 6 union all select 7 union all select 8 union all select 9) as d\n" +
            "            ) a\n" +
            "            where exists ( SELECT 1 FROM reservation where product_id = ?1 and a.Date > start_date and a.Date < end_date)\n" +
            "\t\t\torder by YEAR(a.Date), MONTH(a.Date), DAY(a.Date);", nativeQuery = true)
    List<Date> getAvailableDays(Integer id);


    @Query("FROM Product AS p WHERE p.id NOT IN ( SELECT r.product.id FROM Reservation r WHERE NOT (?1 <= r.startDate OR ?2 >=r.endDate) ) AND p.address.city.name = ?3")
    List<Product> findBySearchedDates(LocalDate endDate, LocalDate startDate, String city);
}
