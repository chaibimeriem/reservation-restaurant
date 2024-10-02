package reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reservation.entity.Reservation;
import reservation.feign.RestaurantClient;
import reservation.service.ReservationService;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

	@Autowired
	private ReservationService reservationService;

	@Autowired
	private RestaurantClient restaurantClient;

	@GetMapping
    public ResponseEntity<Page<Reservation>> getAllReservations(Pageable pageable) {
        Page<Reservation> reservations = reservationService.getAllReservations(pageable);
        return ResponseEntity.ok(reservations);
    }
	
	@GetMapping
	public ResponseEntity<List<Reservation>> getReservations(@RequestParam String customerName) {
		return ResponseEntity.ok(reservationService.getReservations(customerName));
	}

	@PostMapping
	public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
		return ResponseEntity.ok(reservationService.createReservation(reservation));
	}

	@PutMapping
	public ResponseEntity<Reservation> updateReservation(@RequestBody Reservation reservation) {
		// Check the availability of the restaurant
		boolean isAvailable = restaurantClient.checkAvailability(reservation.getRestaurantId(),
				reservation.getNbCustomer()).getBody().booleanValue();

		if (isAvailable) {
			return ResponseEntity.ok(reservationService.updateReservation(reservation));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> cancelReservation(@PathVariable Long id) {
		reservationService.cancelReservation(id);
		return ResponseEntity.ok().build();
	}
}
