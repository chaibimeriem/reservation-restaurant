package reservation.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "restaurant-service", url = "")
public interface RestaurantClient {

	@GetMapping("/restaurants/{id}/availability")
	ResponseEntity<Boolean> checkAvailability(@PathVariable Long id, @RequestParam int nbTables);
}
