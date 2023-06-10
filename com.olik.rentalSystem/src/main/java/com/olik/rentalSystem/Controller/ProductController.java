package com.olik.rentalSystem.Controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olik.rentalSystem.Entity.Booking;
import com.olik.rentalSystem.Entity.Product;
import com.olik.rentalSystem.Repository.BookingRepository;
import com.olik.rentalSystem.Repository.ProductRepository;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	   private final ProductRepository productRepository;
	    private final BookingRepository bookingRepository;

	    @Autowired
	    public ProductController(ProductRepository productRepository, BookingRepository bookingRepository) {
	        this.productRepository = productRepository;
	        this.bookingRepository = bookingRepository;
	    }

	    @GetMapping
	    public ResponseEntity<?> getAllProducts() {
	        List<Product> availableProducts = productRepository.findAll();

	        // Retrieve the current date and time
	        LocalDateTime currentDate = LocalDateTime.now();

	        // Filter out products that are already booked and have overlapping booking dates
	        List<Product> filteredProducts = availableProducts.stream()
	                .filter(product -> {
	                    List<Booking> overlappingBookings = bookingRepository.findByProductIdAndDatesOverlap(
	                            product.getId(), currentDate, currentDate.plusDays(product.getDurationInDays()));
	                    return overlappingBookings.isEmpty();
	                })
	                .collect(Collectors.toList());

	        // Customize the API response based on your requirements
	        return ResponseEntity.ok(filteredProducts);
	    }

	    @PostMapping("/booking")
	    public ResponseEntity<?> createBooking(@RequestBody BookingRequest request) {
	        // Validate the input data
	        if (request.getProductId() == null || request.getDurationInDays() == 0) {
	            return ResponseEntity.badRequest().body("Product ID and duration are required");
	        }

	        // Check if the product exists in the database
	        Optional<Product> optionalProduct = productRepository.findById(request.getProductId());
	        if (optionalProduct.isEmpty()) {
	            return ResponseEntity.badRequest().body("Invalid product ID");
	        }

	        Product product = optionalProduct.get();

	        // Retrieve the current date and time
	        LocalDateTime currentDate = LocalDateTime.now();

	        // Check product availability for the specified duration
	        LocalDateTime endDate = currentDate.plusDays(request.getDurationInDays());
	        List<Booking> overlappingBookings = bookingRepository.findByProductIdAndDatesOverlap(
	                request.getProductId(), currentDate, endDate);
	        if (!overlappingBookings.isEmpty()) {
	            return ResponseEntity.badRequest().body("Product is not available for the specified duration");
	        }

	        // Create a new booking
	        Booking booking = new Booking();
	        booking.setProduct(product);
	        booking.setStartDate(currentDate);
	        booking.setEndDate(endDate);

	        // Save the booking in the database
	        bookingRepository.save(booking);

	        // Customize the API response based on your requirements
	        return ResponseEntity.ok("Booking created successfully");
	    }
}
