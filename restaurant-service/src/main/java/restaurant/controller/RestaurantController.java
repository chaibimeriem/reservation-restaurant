package restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import restaurant.entity.Restaurant;
import restaurant.service.RestaurantService;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
	@Autowired
	private RestaurantService restaurantService;

	@GetMapping
	public ResponseEntity<List<Restaurant>> getRestaurants(@RequestParam String location) {
		return ResponseEntity.ok(restaurantService.findRestaurants(location));
	}

	@GetMapping
	public ResponseEntity<Boolean> isAvailable(@RequestParam Long id, @RequestParam int nbCustomer) {
		return ResponseEntity.ok(restaurantService.isAvailable(id, nbCustomer));
	}

	@PostMapping
	public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant restaurant) {
		return ResponseEntity.ok(restaurantService.addRestaurant(restaurant));
	}

	@PutMapping("/{id}/availability")
	public ResponseEntity<Void> updateAvailability(@PathVariable Long id, @RequestParam int availableTables) {
		restaurantService.updateAvailability(id, availableTables);
		return ResponseEntity.noContent().build();
	}
}
