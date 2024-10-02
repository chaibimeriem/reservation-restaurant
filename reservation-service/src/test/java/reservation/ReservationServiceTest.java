package reservation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import reservation.entity.Reservation;
import reservation.repository.ReservationRepository;
import reservation.service.ReservationService;

@SpringBootTest
public class ReservationServiceTest {
	@Autowired
	private ReservationService reservationService;

	@MockBean
	private ReservationRepository reservationRepository;

	@Test
	public void testAddReservation() {
		Reservation reservation = new Reservation();
		reservation.setName("Test");
		reservation.setReservationTime(LocalDateTime.now());

		Reservation savedReservation = reservationService.createReservation(reservation);
		assertEquals("Test", savedReservation.getName());
		// ...
	}
}