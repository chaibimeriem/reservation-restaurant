package reservation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import restaurant.entity.Restaurant;
import restaurant.repository.RestaurantRepository;
import restaurant.service.RestaurantService;

@SpringBootTest
public class RestaurantServiceTest {
	@Autowired
	private RestaurantService restaurantService;

	@MockBean
	private RestaurantRepository restaurantRepository;

	@Test
	public void testAddRestaurant() {
		Restaurant restaurant = new Restaurant();
		restaurant.setName("XXX");
		restaurant.setLocation("Marseille");

		Restaurant savedRestaurant = restaurantService.addRestaurant(restaurant);
		assertEquals("XXX", savedRestaurant.getName());
		// ...
	}
}