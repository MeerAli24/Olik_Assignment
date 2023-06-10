package com.olik.rentalSystem.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.olik.rentalSystem.Entity.Booking;
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>{
	List<Booking> findByStartDateLessThanEqualAndEndDateGreaterThanEqual(LocalDateTime startDate, LocalDateTime endDate);
	
	
	  @Query("SELECT b FROM Booking b WHERE b.product.id = :productId AND " +
	            "b.startDate <= :endDate AND b.endDate >= :startDate")
	    List<Booking> findByProductIdAndDatesOverlap(
	            @Param("productId") Long productId,
	            @Param("startDate") LocalDateTime startDate,
	            @Param("endDate") LocalDateTime endDate);
}
