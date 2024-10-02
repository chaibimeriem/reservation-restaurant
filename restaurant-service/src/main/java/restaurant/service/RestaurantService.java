package restaurant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import restaurant.entity.Restaurant;
import restaurant.repository.RestaurantRepository;

@Service
public class RestaurantService {
	@Autowired
	private RestaurantRepository restaurantRepository;

	public boolean isAvailable(Long id, int nbCustomer) {
		return restaurantRepository.findById(id).get().getAvailableTables() >= nbCustomer;
	}

	public List<Restaurant> findRestaurants(String location) {
		return restaurantRepository.findByLocation(location);
	}

	public Restaurant addRestaurant(Restaurant restaurant) {
		return restaurantRepository.save(restaurant);
	}

	public void updateAvailability(Long id, int availableTables) {
		Restaurant restaurant = restaurantRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Restaurant not found"));
		restaurant.setAvailableTables(availableTables);
		restaurantRepository.save(restaurant);
	}
}
