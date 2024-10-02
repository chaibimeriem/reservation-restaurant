package reservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import reservation.entity.Reservation;
import reservation.repository.ReservationRepository;

@Service
public class ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;

	 // Get with pagination
    public Page<Reservation> getAllReservations(Pageable pageable) {
        return reservationRepository.findAll(pageable);
    }
    
	public List<Reservation> getReservations(String customerName) {
		return reservationRepository.findByName(customerName);
	}

	public Reservation getReservation(Long id) {
		return reservationRepository.findById(id).orElse(null);
	}

	public Reservation createReservation(Reservation reservation) {
		if (reservation.getId() != null && reservationRepository.existsById(reservation.getId())) {
			throw new EntityExistsException();
		}
		return reservationRepository.save(reservation);

	}

	public Reservation updateReservation(Reservation reservation) {
		if (reservationRepository.existsById(reservation.getId())) {
			return reservationRepository.save(reservation);
		}
		throw new EntityNotFoundException();
	}

	public void cancelReservation(Long id) {
		reservationRepository.deleteById(id);
	}
}
