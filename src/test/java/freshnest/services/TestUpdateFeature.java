package freshnest.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import freshnest.model.User;
import freshnest.services.exceptions.ServiceException;

public class TestUpdateFeature {

	public static void main(String[] args) {

		User user1 = new User("susi@gmail.com", "Kanipapa", "raji@SM123", "Susikumar", "Pitchaimuthu", 20,
				8870737612L, "2003-08-01", "Indian", "Male");

		UserService userService = new UserService();

		try {
			userService.UpdateUser(user1);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateSuccess() {

		User user1 = new User("susikumar@gmail.com", "Kanipapa", "raji@SM123", "Susikumar", "Pitchaimuthu", 20,
				8870737612L, "2003-08-01", "Indian", "Male");

		UserService userService = new UserService();

		try {
			userService.UpdateUser(user1);
		} catch (ServiceException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testInavalidUpdateSuccess() {
		UserService userService = new UserService();

		User user1 = new User("mani@gmail.com", "Kanipapa", "raji@SM123", "Susikumar", "Pitchaimuthu", 20, 8870737612L,
				"2003-08-01", "Indian", "Male23");
		try {
			assertFalse(userService.UpdateUser(user1));
		} catch (ServiceException e) {
			e.printStackTrace();

		}
	}
}
